package com.example.kursach301.viewModels

import android.util.Log
import androidx.databinding.ObservableChar
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.DHT11UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DHT11ViewModel @Inject constructor(dht11UseCase: DHT11UseCase): ViewModel() {
    var i =0
    var textValue = MutableLiveData("Начальное значение")


    fun testOnClick() {
        Log.d("click", "DHT11CLICK")
        textValue.value = "Не Начальное значение"
        i++
        Log.d("click", this.textValue.toString())
    }

    //private val context = getApplication<Application>().applicationContext
    private val macMK: String = "F8:30:02:00:22:1A"
    //private lateinit var bluetoothGattCharacteristic: BluetoothGattCharacteristic //получаем в connect
    private val descriptorUUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    private val serviceUUID= UUID.fromString("0000FFE0-0000-1000-8000-00805F9B34FB")
    private val characteristicUUID= UUID.fromString("0000FFE1-0000-1000-8000-00805F9B34FB")
    private val WRITE_TEST_TEMP_AND_HUMIDITY="8"


    //var bufferForTempAndHum: MutableLiveData<String?> = MutableLiveData(null)

//    fun getTempAndHumidity(){
//        viewModelScope.launch {
//            try {
//                //инициализация соединения
//                val connection= GattConnection(deviceFor(macMK))
//                connection.logConnectionChanges() //логирование процесса
//                connection.connect() //попытка подключения
//                val services=connection.discoverServices()
//                //получение всех возможных сервисов устройства
//                services.forEach { it ->
//                    if (it.uuid == serviceUUID) {
//                        it.characteristics.forEach {
//                            if (it.uuid == characteristicUUID) {
//                                this@TempHumidityViewModel.bluetoothGattCharacteristic=it
//                                //перебираем и получаем необходимую характеристику
//                            }
//                        }
//                    }
//                }
//                //оповещаем устройство о том, что оно может обновлять нашу хар-ку
//                setCharacteristicNotification(connection,bluetoothGattCharacteristic,true)
//                val msg: ByteArray = WRITE_TEST_TEMP_AND_HUMIDITY.toByteArray() //формирование сообщения
//                bluetoothGattCharacteristic.value = msg //запись в характеристику данных локально
//                Log.d("characterInSend",bluetoothGattCharacteristic.value.toString())
//                connection.writeCharacteristic(bluetoothGattCharacteristic) //запись характеристики на устройство
//                connection.notifyChannel.receive() //обновление канала данных
//                //в переменную кладётся полученный с устройства ответ - с датчика
//                bufferForTempAndHum.value=bluetoothGattCharacteristic.getStringValue(0)
//                connection.close() //окончание соединения
//            }
//            catch (e: Exception) {
//                Log.e("ошибка",e.toString()) //обработка ошибки
//            }
//        }
//    }

//    private fun deviceFor(macAddress: String): BluetoothDevice = //получение BluetoothDevice
//        bluetoothManager.adapter.getRemoteDevice(macAddress)
//
//    private fun GattConnection.logConnectionChanges() {
//        GlobalScope.launch(Dispatchers.Main) {
//            stateChangeChannel.consumeEach {
//                Log.d("tag","connection state changed: $it")
//            }
//        }
//    }
//
//    private suspend fun setCharacteristicNotification(
//        bluetoothGatt: GattConnection,
//        characteristic: BluetoothGattCharacteristic,
//        enable: Boolean
//    ) {
//        //Logger.d("setCharacteristicNotification")
//        bluetoothGatt.setCharacteristicNotificationsEnabled(bluetoothGattCharacteristic,true)
//        val descriptor =
//            characteristic.getDescriptor(descriptorUUID)
//        if (enable) descriptor.value= BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else descriptor.value=byteArrayOf(
//            0x00,
//            0x00
//        )
//        bluetoothGatt.writeDescriptor(descriptor)
//    }


    }