package com.example.kursach301.viewModels

import android.util.Log
import androidx.databinding.ObservableChar
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.DHT11UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DHT11ViewModel @Inject constructor(val dht11UseCase: DHT11UseCase): ViewModel() {
    var tempAndHumFromDht11:MutableLiveData<String> = MutableLiveData("")



    fun getTempFromDht11() {
        viewModelScope.launch {
            val temp = dht11UseCase.getTestString()
           // tempAndHumFromDht11.value =
            Log.d("testClean",temp)
        }

    }



    }
