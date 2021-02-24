package com.example.domain.usecases

import com.example.domain.repositories.I_DHT11UseCase
import javax.inject.Inject

class DHT11UseCase @Inject constructor(private val IdhT11UseCase: I_DHT11UseCase) {
    suspend fun getTestString():String {
        return IdhT11UseCase.test()
    }
}