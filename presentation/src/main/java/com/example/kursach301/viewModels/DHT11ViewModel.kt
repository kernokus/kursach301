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
class DHT11ViewModel @Inject constructor(val bleUseCase: bleUseCase, val constants:Constants): ViewModel() {
    var tempAndHumFromDht11:MutableLiveData<String> = MutableLiveData("")



    fun getTempFromDht11() {
        tempAndHumFromDht11.value = ""
        Log.d("click","click")
        viewModelScope.launch {
            val temp = bleUseCase.getTemp(constants.characteristicDht11UUID,constants.serviceUUID,constants.DHT11_REQUEST_CODE)
            tempAndHumFromDht11.value = "Температура с датчика DHT11: $temp °C"
            Log.d("testTemp",temp)
            Log.d("testVM", tempAndHumFromDht11.value!!)
        }

    }



    }
