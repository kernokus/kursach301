package com.example.kursach301.viewModels



import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constants
import com.example.domain.usecases.bleUseCase
import com.example.domain.usecases.bleUseCase3Sensors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimultaneousMeasurementViewModel @Inject constructor(val bleUseCase: bleUseCase3Sensors): ViewModel() {
    var tempFromDht11:MutableLiveData<String> = MutableLiveData("")
    var tempFromTmp36:MutableLiveData<String> = MutableLiveData("")
    var tempFromDs18b20:MutableLiveData<String> = MutableLiveData("")

    fun getTemperatureFrom3Sensors() {
        refreshMLD()
        viewModelScope.launch {
            val temp :Triple<String,String,String> = bleUseCase.getTemperature()
            tempFromDht11.value = "Температура с датчика DHT11: ${temp.first} °C"
            tempFromTmp36.value = "Температура с датчика TMP36: ${temp.second} °C"
            tempFromDs18b20.value = "Температура с датчика DS18B20: ${temp.third} °C"
            Log.d("testTemp", temp.toString())

        }
    }

    fun refreshMLD() {
        tempFromDht11.value=""
        tempFromTmp36.value=""
        tempFromDs18b20.value=""
    }



}
