package com.example.domain.repositories

import java.util.*

interface I_MainBleUseCase {
    suspend fun getTemperature(characteristicUUID: UUID, serviceUUID: UUID, requestCommand:String):String
}