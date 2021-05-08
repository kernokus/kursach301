package com.example.data.repoImpl

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.util.Log
import com.beepiz.bluetooth.gattcoroutines.ExperimentalBleGattCoroutinesCoroutinesApi
import com.beepiz.bluetooth.gattcoroutines.GattConnection
import com.example.domain.repositories.I_MainBleUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import splitties.systemservices.bluetoothManager
import java.util.*
import javax.inject.Inject

class DHT11RepoImpl @Inject constructor() : I_MainBleUseCase {
    private val macMK: String = "AC:67:B2:3C:8F:76"
    private val descriptorUUID = convertFromInteger(0x2902)

    lateinit var bluetoothGattCharacteristic: BluetoothGattCharacteristic

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @ExperimentalBleGattCoroutinesCoroutinesApi
    override suspend fun getTemperature(characteristicUUID:UUID,serviceUUID:UUID,requestCommand:String): String {
        var response = ""
        try {
            //инициализация соединения
            val connection = GattConnection(deviceFor(macMK))
            connection.logConnectionChanges() //логирование процесса
            connection.connect() //попытка подключения
            Log.d("connectFailed", "yep")
            val services = connection.discoverServices()
            //получение всех возможных сервисов устройства
            services.forEach { it ->
                Log.d("serviceBLE", it.toString())
                if (it.uuid == serviceUUID) {
                    it.characteristics.forEach {
                        if (it.uuid == characteristicUUID) {
                            bluetoothGattCharacteristic = it //
                            //перебираем и получаем необходимую характеристику
                        }
                    }
                }
            }
            setCharacteristicNotification(
                connection,
                bluetoothGattCharacteristic,
                true
            ) //оповещаем устройство о том, что оно может обновлять нашу хар-ку
            val msg: ByteArray = requestCommand.toByteArray() //формирование сообщения
            bluetoothGattCharacteristic.value = msg //запись в характеристику данных локально
            Log.d("characterInSend", bluetoothGattCharacteristic.value.toString())
            connection.writeCharacteristic(bluetoothGattCharacteristic) //запись характеристики на устройство
            Log.d("HERE1", "HERE1")
            //connection.notifyChannel. //обновление канала данных
            Log.d("HERE2", "HERE2")
            //в переменную кладётся полученный с устройства ответ - с датчика
            delay(100)
            response = bluetoothGattCharacteristic.getStringValue(0)
            Log.d("response1", response)
            connection.close() //окончание соединения
        } catch (e: Exception) {
            Log.e("ErrorInConnect", e.toString())
        }
        Log.d("response2", response)
        return response
    }


    private fun deviceFor(macAddress: String): BluetoothDevice = //получение BluetoothDevice
        bluetoothManager.adapter.getRemoteDevice(macAddress)

    private fun GattConnection.logConnectionChanges() { //для системы логирования
        GlobalScope.launch(Dispatchers.Main) {
            stateChangeChannel.consumeEach {
                Log.d("tag", "connection state changed: $it")
            }
        }
    }

    //
    private suspend fun setCharacteristicNotification(
        bluetoothGatt: GattConnection,
        characteristic: BluetoothGattCharacteristic,
        enable: Boolean
    ) {
        bluetoothGatt.setCharacteristicNotificationsEnabled(bluetoothGattCharacteristic, true)
        val descriptor =
            characteristic.getDescriptor(descriptorUUID)
        if (enable) descriptor.value =
            BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else descriptor.value = byteArrayOf(
            0x00,
            0x00
        )
        bluetoothGatt.writeDescriptor(descriptor)
    }



    fun convertFromInteger(i: Int): UUID? {
        val MSB = 0x0000000000001000L
        val LSB = -0x7fffff7fa064cb05L
        val value = (i and -0x1).toLong()
        return UUID(MSB or (value shl 32), LSB)
    }
}





