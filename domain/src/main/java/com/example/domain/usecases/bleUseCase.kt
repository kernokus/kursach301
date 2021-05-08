package com.example.domain.usecases

import com.example.domain.repositories.I_MainBleUseCase
import java.util.*
import javax.inject.Inject

class bleUseCase @Inject constructor(private val IBleUseCase: I_MainBleUseCase) {
    suspend fun getTemp(characteristicUUID: UUID, serviceUUID: UUID, requestCommand:String):String {
        return IBleUseCase.getTemperature(characteristicUUID,serviceUUID,requestCommand)
    }
}