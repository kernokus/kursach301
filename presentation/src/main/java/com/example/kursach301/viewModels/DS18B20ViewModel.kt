package com.example.kursach301.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constants
import com.example.domain.usecases.bleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DS18B20ViewModel @Inject constructor(val bleUseCase: bleUseCase, val constants:Constants): ViewModel() {
    var tempFromDs18b20:MutableLiveData<String> = MutableLiveData("")

    fun getTemperatureFromDs18b20() {
        tempFromDs18b20.value=""
        Log.d("click","click")
        viewModelScope.launch {
            val temp = bleUseCase.getTemp(constants.characteristicDs18b20UUID,constants.serviceUUID,constants.DS18B20_REQUEST_CODE)
            tempFromDs18b20.value ="Температура с датчика DS18B20: $temp °C"
            Log.d("testTemp",temp)
            Log.d("testVM", tempFromDs18b20.value!!)
        }
    }



}
