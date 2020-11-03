package com.example.kursach301.viewModels

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattService
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beepiz.bluetooth.gattcoroutines.ExperimentalBleGattCoroutinesCoroutinesApi
import com.beepiz.bluetooth.gattcoroutines.GattConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import splitties.systemservices.bluetoothManager
import java.util.*


@OptIn(ExperimentalBleGattCoroutinesCoroutinesApi::class)
class SampleTransmissionViewModel(application: Application):AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val macMK: String = "F8:30:02:00:22:1A"
    private val serviceUUID= UUID.fromString("0000FFE0-0000-1000-8000-00805F9B34FB")
    private val characteristicUUID= UUID.fromString("0000FFE1-0000-1000-8000-00805F9B34FB")
    private val descriptorUUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    //val arr = byteArrayOf(5.toByte(),7.toByte(),8.toByte(),1.toByte(),8.toByte(),0.toByte(),2.toByte(),3.toByte())
    val arr = byteArrayOf(10.toByte())

    private val WRITE_TEST_WORD="9"

    var bufferLD: MutableLiveData<String? > = MutableLiveData(null)

    var connection:GattConnection=GattConnection(deviceFor(macMK))

    lateinit var  gattServices : List<BluetoothGattService> //заполняются при connect
    lateinit var bluetoothGattCharacteristic: BluetoothGattCharacteristic //получаем в connect




    fun disconnect() {
        viewModelScope.launch {
            try {
                connection.close()
            }
            catch (e: Exception) {
                Log.e("ошибкаДисконекта",e.toString())
            }
        }
    }


    fun connect()  {
        viewModelScope.launch {
            try {
                connection=GattConnection(deviceFor(macMK))
                connection.logConnectionChanges() //логирование процесса
                connection.connect()
                this@SampleTransmissionViewModel.gattServices=connection.discoverServices()
                gattServices.forEach { it ->
                    if (it.uuid == serviceUUID) {
                        it.characteristics.forEach {
                            if (it.uuid == characteristicUUID) {
                                this@SampleTransmissionViewModel.bluetoothGattCharacteristic=it
                            }
                        }
                    }
                }
                setCharacteristicNotification(connection,bluetoothGattCharacteristic,true)
            }
            catch (e: Exception) {
                Log.e("ошибкаКонекта",e.toString())
            }
        }
    }


    fun send() {
        try {
            viewModelScope.launch {
                val msg: ByteArray = WRITE_TEST_WORD.toByteArray()
                bluetoothGattCharacteristic.value = msg
                Log.d("characterInSend",bluetoothGattCharacteristic.value.toString())
                connection.writeCharacteristic(bluetoothGattCharacteristic)
                connection.notifyChannel
            }
        }
        catch (e:Exception){
            Log.e("errorInSend",e.toString())
        }

    }


    fun read() {
        try {
            viewModelScope.launch {
                Log.d("characterInRead",bluetoothGattCharacteristic.getStringValue(0).toString())
                for(item in bluetoothGattCharacteristic.value) {
                    Log.d("InReadEveryByt",item.toString())
                }
                val temp=bluetoothGattCharacteristic.getStringValue(0)
                Log.d("temp",temp.toString())
                if (temp!=null) {
                    this@SampleTransmissionViewModel.bufferLD.value =temp
                    Log.d("temp", bufferLD.value.toString())
                }
            }
        }
        catch (e:Exception) {
            Log.e("errorInRead",e.toString())
        }

    }


//////////////////////////////////




    private fun GattConnection.logConnectionChanges() {
        GlobalScope.launch(Dispatchers.Main) {
            stateChangeChannel.consumeEach {
                Log.d("tag","connection state changed: $it")
            }
        }
    }

    private fun deviceFor(macAddress: String): BluetoothDevice = //получение BluetoothDevice
        bluetoothManager.adapter.getRemoteDevice(macAddress)

    private suspend fun setCharacteristicNotification(
        bluetoothGatt: GattConnection,
        characteristic: BluetoothGattCharacteristic,
        enable: Boolean
    ) {
        //Logger.d("setCharacteristicNotification")
        bluetoothGatt.setCharacteristicNotificationsEnabled(bluetoothGattCharacteristic,true)
        val descriptor =
            characteristic.getDescriptor(descriptorUUID)
        if (enable) descriptor.value=BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else descriptor.value=byteArrayOf(
            0x00,
            0x00
        )
        bluetoothGatt.writeDescriptor(descriptor) 
    }


}




