package com.example.domain.usecases

import com.example.domain.repositories.I_BleUseCase3Sensors
import com.example.domain.repositories.I_MainBleUseCase
import java.util.*
import javax.inject.Inject

class bleUseCase3Sensors @Inject constructor(private val IBleUseCase3Sensors: I_BleUseCase3Sensors) {
    suspend fun getTemperature():Triple<String,String,String> {
        return IBleUseCase3Sensors.getTemperatureFrom3Sensors()
    }
}