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
class TMP36ViewModel @Inject constructor(val bleUseCase: bleUseCase, val constants: Constants): ViewModel() {
    var tempFromTmp36:MutableLiveData<String> = MutableLiveData("")


    fun getTemperatureFromTmp36() {
        tempFromTmp36.value=""
        Log.d("click","click")
        viewModelScope.launch {
            val temp = bleUseCase.getTemp(constants.characteristicTmp36UUID,constants.serviceUUID,constants.TMP36_REQUEST_CODE)
            tempFromTmp36.value ="Температура с датчика TMP36: $temp °C"
            Log.d("TMP36",temp)
            Log.d("testVM", tempFromTmp36.value!!)
        }

    }


}




