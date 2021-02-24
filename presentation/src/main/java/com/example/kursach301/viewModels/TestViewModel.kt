//package com.example.kursach301.viewModels
//
//import android.app.Application
//import android.bluetooth.*
//import android.util.Log
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
//import com.beepiz.bluetooth.gattcoroutines.ExperimentalBleGattCoroutinesCoroutinesApi
//import com.beepiz.bluetooth.gattcoroutines.GattConnection
//import com.beepiz.bluetooth.gattcoroutines.extensions.requireCharacteristic
//import kotlinx.coroutines.*
//import kotlinx.coroutines.channels.consumeEach
//import splitties.systemservices.bluetoothManager
//import splitties.toast.toast
//import java.util.*
//
//
//@OptIn(ExperimentalBleGattCoroutinesCoroutinesApi::class)
//class TestViewModel(application: Application):AndroidViewModel(application) {
//    private val context = getApplication<Application>().applicationContext
//    private val macMK: String = "F8:30:02:00:22:1A"
//    private val serviceUUID=UUID.fromString("0000FFE0-0000-1000-8000-00805F9B34FB")
//    private val characteristicUUID=UUID.fromString("0000FFE1-0000-1000-8000-00805F9B34FB")
//    //val arr = byteArrayOf(5.toByte(),7.toByte(),8.toByte(),1.toByte(),8.toByte(),0.toByte(),2.toByte(),3.toByte())
//    val arr = byteArrayOf(10.toByte())
//
//    //private val  serviceUUID: UUID =UUID.fromString(serviceString.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"))
//    //val characteristicUUID:UUID=UUID.fromString(characteristicString.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"))
//
//
//    var  connection:GattConnection=GattConnection(deviceFor(macMK))
//    //var bluetoothGattCharacteristic: BluetoothGattCharacteristic=connection.requireCharacteristic(serviceUuid = serviceUUID,characteristicUuid = characteristicUUID)
//
//    lateinit var bluetoothGattCharacteristic: BluetoothGattCharacteristic
//
//    init {
//    //connection.setCharacteristicNotificationsEnabled(bluetoothGattCharacteristic,true)!!
//    }
//
//     val CHARACTERISTIC_UPDATE_NOTIFICATION_DESCRIPTOR_UUID =
//        UUID.fromString("00002902-0000-1000-8000-00805f9b34fb") //для дескриптора тест
//
//    fun disconnect() {
//        viewModelScope.launch {
//            try {
//
//
//
//
//            }
//            catch (e: Exception) {
//                Log.e("ошибкаДисконекта",e.toString())
//            }
//        }
//    }
//
//
//    fun connect()  {
//        viewModelScope.launch {
//            try {
//                connection.connect()
//                val gattServices = connection.discoverServices()
//                gattServices.forEach {
//                    if (it.uuid == serviceUUID) {
//                        it.characteristics.forEach {
//                            if (it.uuid == characteristicUUID) {
//                                this@TestViewModel.bluetoothGattCharacteristic = it
//                            }
//                        }
//                    }
//                }
//                setCharacteristicNotification(connection,bluetoothGattCharacteristic,true)
//                //connection.openNotificationSubscription(characteristic =bluetoothGattCharacteristic)///////////////////////////
//
//
////                connection.setCharacteristicNotificationsEnabled(this@StartingViewModel2.bluetoothGattCharacteristic,true)
////                val descriptor: BluetoothGattDescriptor = bluetoothGattCharacteristic.getDescriptor(CHARACTERISTIC_UPDATE_NOTIFICATION_DESCRIPTOR_UUID)
////                descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
////                connection.writeDescriptor(descriptor)
//
//
//
//                    //Log.d("check1",bluetoothGattCharacteristic.getStringValue(0).toString())
//                val str="1"
//                val tx: ByteArray = str.toByteArray()
//
//                bluetoothGattCharacteristic.setValue(tx)
//                    connection.writeCharacteristic(bluetoothGattCharacteristic)
//                connection.notifyChannel.receive()
//                    Log.d("check2AfterWright",bluetoothGattCharacteristic.getStringValue(0).toString())
//                for(item in bluetoothGattCharacteristic.value) {
//                   Log.d("checkBYTESTART",item.toString())
//                }
//
////                val Gatservices=connection.discoverServices()
////                Gatservices.forEach {
////                        it.characteristics.forEach {
////                            it?.getStringValue(0)?.let { it1 -> Log.d("CHECKNEWGATSERVICES", it1) }
////                        }
////
////                }
//
//
//
//
//                    //connection.readCharacteristic(bluetoothGattCharacteristic)
//
//
////                for(item in bluetoothGattCharacteristic.value) {
////                    Log.d("checkBYTE",item.toString())
////                }
////                    Log.d("check3afterread",bluetoothGattCharacteristic.value.decodeToString())
////                val tempo=connection.requireCharacteristic(serviceUUID,characteristicUUID).value
////                for(item in tempo) {
////                    Log.d("checkBYTETEMPOOO",item.toString())
////                }
//                //Log.d("check3afterread",bluetoothGattCharacteristic.value.get(1).toString())
//                //Log.d("check3afterread",bluetoothGattCharacteristic.value.get(2).toString())
//
//
//
//            }
//            catch (e:Exception) {
//                Log.e("ошибкаПодключения",e.toString())
//            }
//            finally {
//                connection.close()
//                connection=GattConnection(deviceFor(macMK))
//            }
//        }
//
//        //Log.d("temp",temp.toString())
//
//    }
//
//    suspend fun setCharacteristicNotification(
//        bluetoothGatt: GattConnection,
//        characteristic: BluetoothGattCharacteristic,
//        enable: Boolean
//    ): Unit {
//        //Logger.d("setCharacteristicNotification")
//        bluetoothGatt.setCharacteristicNotificationsEnabled(bluetoothGattCharacteristic,true)
//        val descriptor =
//            characteristic.getDescriptor(CHARACTERISTIC_UPDATE_NOTIFICATION_DESCRIPTOR_UUID)
//        if (enable) descriptor.value=BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else descriptor.value=byteArrayOf(
//            0x00,
//            0x00
//        )
//        bluetoothGatt.writeDescriptor(descriptor) //descriptor write operation successfully started?
//    }
//
//
//    fun send() {
//        try {
//            viewModelScope.launch {
//            connection.connect()
//
//            }
//        }
//        catch (e:Exception){
//            Log.e("ошибкаВWrite",e.toString())
//        }
//
//    }
//
//
//    fun read() {
//        try {
//            viewModelScope.launch {
//                //connection.connect()
//                val gattServices = connection.discoverServices()
//                bluetoothGattCharacteristic=connection.requireCharacteristic(serviceUUID,characteristicUUID)
////                for(item in bluetoothGattCharacteristic.value) {
////                    Log.d("checkBYTEinREAD",item.toString())
////                }
//
//                connection.readCharacteristic(bluetoothGattCharacteristic)
//
//                for(item in bluetoothGattCharacteristic.value) {
//                    Log.d("checkBYTEinREAD222",item.toString())
//                }
//
//                connection.close()
//
//
//
//
//
//
//
//
//               // Log.d("CHECKNEW")
//            }
//        }
//        catch (e:Exception) {
//            Log.e("ошибкаВRead",e.toString())
//        }
//
//
//    }
//
//
//
//
//
//
//
//    fun GattConnection.logConnectionChanges() {
//        GlobalScope.launch(Dispatchers.Main) {
//            stateChangeChannel.consumeEach {
//                Log.d("tag","connection state changed: $it")
//            }
//        }
//    }
//
//    fun deviceFor(macAddress: String): BluetoothDevice = //получение BluetoothDevice
//        bluetoothManager.adapter.getRemoteDevice(macAddress)
//
//    suspend inline fun BluetoothDevice.useBasic(
//        connectionTimeoutInMillis: Long = 5000L,
//        block: (GattConnection, List<BluetoothGattService>) -> Unit
//    ) {
//        val deviceConnection = GattConnection(this)
//        try {
//            deviceConnection.logConnectionChanges()
//            withTimeout(connectionTimeoutInMillis) {
//                deviceConnection.connect()
//            }
//            Log.d("tag","Connected!")
//            val services = deviceConnection.discoverServices()
//            Log.d("tag","Services discovered!")
//            block(deviceConnection, services)
//        } catch (e: TimeoutCancellationException) {
//            Log.d("tag","Connection timed out after $connectionTimeoutInMillis milliseconds!".also {
//                toast(it)
//            })
//            throw e
//        } catch (e: CancellationException) {
//            throw e
//        } catch (e: Exception) {
//            Log.d("t",e.toString())
//        } finally {
//            deviceConnection.close()
//            Log.d("tag","Closed!")
//        }
//    }
//
//
//
//}
//
//
//
//
