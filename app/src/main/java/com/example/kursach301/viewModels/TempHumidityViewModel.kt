package com.example.kursach301.viewModels

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beepiz.bluetooth.gattcoroutines.GattConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import splitties.systemservices.bluetoothManager
import java.util.*

class TempHumidityViewModel (application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val macMK: String = "F8:30:02:00:22:1A"
    private lateinit var bluetoothGattCharacteristic: BluetoothGattCharacteristic //получаем в connect
    private val descriptorUUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    private val serviceUUID= UUID.fromString("0000FFE0-0000-1000-8000-00805F9B34FB")
    private val characteristicUUID= UUID.fromString("0000FFE1-0000-1000-8000-00805F9B34FB")
    private val WRITE_TEST_TEMP_AND_HUMIDITY="8"


    var bufferForTempAndHum: MutableLiveData<String?> = MutableLiveData(null)

    fun getTempAndHumidity(){
        viewModelScope.launch {
            try {
                val connection= GattConnection(deviceFor(macMK)) //инициализация соединения
                connection.logConnectionChanges() //логирование процесса
                connection.connect() //попытка подключения
                val services=connection.discoverServices() //получение всех возможных сервисов устройства
                services.forEach { it ->
                    if (it.uuid == serviceUUID) {
                        it.characteristics.forEach {
                            if (it.uuid == characteristicUUID) {
                                this@TempHumidityViewModel.bluetoothGattCharacteristic=it //перебираем и получаем необходимую характеристику
                            }
                        }
                    }
                }
                setCharacteristicNotification(connection,bluetoothGattCharacteristic,true) //оповещаем устройство о том, что оно может обновлять нашу хар-ку
                val msg: ByteArray = WRITE_TEST_TEMP_AND_HUMIDITY.toByteArray() //формирование сообщения
                bluetoothGattCharacteristic.value = msg //запись в характеристику данных локально
                Log.d("characterInSend",bluetoothGattCharacteristic.value.toString())
                connection.writeCharacteristic(bluetoothGattCharacteristic) //запись характеристики на устройство
                connection.notifyChannel.receive() //обновление канала данных
                bufferForTempAndHum.value=bluetoothGattCharacteristic.getStringValue(0) //в переменную кладётся полученный с устройства ответ - с датчика
                connection.close() //окончание соединения
            }
            catch (e: Exception) {
                Log.e("ошибка",e.toString()) //обработка ошибки
            }
        }
    }

    private fun deviceFor(macAddress: String): BluetoothDevice = //получение BluetoothDevice
        bluetoothManager.adapter.getRemoteDevice(macAddress)

    private fun GattConnection.logConnectionChanges() {
        GlobalScope.launch(Dispatchers.Main) {
            stateChangeChannel.consumeEach {
                Log.d("tag","connection state changed: $it")
            }
        }
    }

    private suspend fun setCharacteristicNotification(
        bluetoothGatt: GattConnection,
        characteristic: BluetoothGattCharacteristic,
        enable: Boolean
    ) {
        //Logger.d("setCharacteristicNotification")
        bluetoothGatt.setCharacteristicNotificationsEnabled(bluetoothGattCharacteristic,true)
        val descriptor =
            characteristic.getDescriptor(descriptorUUID)
        if (enable) descriptor.value= BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else descriptor.value=byteArrayOf(
            0x00,
            0x00
        )
        bluetoothGatt.writeDescriptor(descriptor)
    }


    }
