package com.example.data.repoImpl

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.util.Log
import com.beepiz.bluetooth.gattcoroutines.GattConnection
import com.example.data.Constants
import com.example.domain.repositories.I_BleUseCase3Sensors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.systemservices.bluetoothManager
import java.util.*
import javax.inject.Inject

class ThreeSensorsRepoImpl @Inject constructor(val constants:Constants) :I_BleUseCase3Sensors {
    private val macMK: String = "AC:67:B2:3C:8F:76"
    private val descriptorUUID = convertFromInteger(0x2902)
    lateinit var bluetoothGattCharacteristicDht11: BluetoothGattCharacteristic
    lateinit var bluetoothGattCharacteristicTmp36: BluetoothGattCharacteristic
    lateinit var bluetoothGattCharacteristicDs18B20: BluetoothGattCharacteristic
    override suspend fun getTemperatureFrom3Sensors(): Triple<String, String, String> {
        var dht11Temp=""
        var tmp36Temp=""
        var ds18b20Temp=""
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
                if (it.uuid == constants.serviceUUID) {
                    it.characteristics.forEach {
                        if (it.uuid == constants.characteristicDht11UUID) {
                            bluetoothGattCharacteristicDht11 = it //
                            //перебираем и получаем необходимую характеристику
                        }
                        else if (it.uuid == constants.characteristicTmp36UUID) {
                            bluetoothGattCharacteristicTmp36 = it
                        }
                        else if (it.uuid == constants.characteristicDs18b20UUID) {
                            bluetoothGattCharacteristicDs18B20 = it
                        }
                    }
                }
            }
            setCharacteristicNotification(connection, bluetoothGattCharacteristicDht11, true) //оповещаем устройство о том, что оно может обновлять нашу хар-ку
            setCharacteristicNotification(connection, bluetoothGattCharacteristicTmp36, true) //оповещаем устройство о том, что оно может обновлять нашу хар-ку
            setCharacteristicNotification(connection, bluetoothGattCharacteristicDs18B20, true) //оповещаем устройство о том, что оно может обновлять нашу хар-ку

            val msgDht11: ByteArray = constants.DHT11_REQUEST_CODE.toByteArray()
            val msgTmp36: ByteArray = constants.TMP36_REQUEST_CODE.toByteArray()
            val msgDs18b20: ByteArray = constants.DS18B20_REQUEST_CODE.toByteArray()
            bluetoothGattCharacteristicDht11.value = msgDht11
            bluetoothGattCharacteristicTmp36.value = msgTmp36
            bluetoothGattCharacteristicDs18B20.value = msgDs18b20
            connection.writeCharacteristic(bluetoothGattCharacteristicDht11)
            connection.writeCharacteristic(bluetoothGattCharacteristicTmp36)
            connection.writeCharacteristic(bluetoothGattCharacteristicDs18B20)
            //connection.notifyChannel. //обновление канала данных
            delay(300)
            dht11Temp = bluetoothGattCharacteristicDht11.getStringValue(0)
            tmp36Temp= bluetoothGattCharacteristicTmp36.getStringValue(0)
            ds18b20Temp = bluetoothGattCharacteristicDs18B20.getStringValue(0)
            connection.close() //окончание соединения
        } catch (e: Exception) {
            Log.e("ErrorInConnect", e.toString())
        }
        return Triple(dht11Temp,tmp36Temp,ds18b20Temp)
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
        bluetoothGatt.setCharacteristicNotificationsEnabled(characteristic, true)
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