package com.example.domain.repositories

import java.util.*

interface I_BleUseCase3Sensors {
    suspend fun getTemperatureFrom3Sensors():Triple<String,String,String>
}