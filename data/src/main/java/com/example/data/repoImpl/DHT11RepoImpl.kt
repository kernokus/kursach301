package com.example.data.repoImpl

import android.util.Log
import com.example.domain.repositories.I_DHT11UseCase
import javax.inject.Inject

class DHT11RepoImpl @Inject constructor() : I_DHT11UseCase {

    override fun connect() {
        Log.d("connect","connect")
    }

    override fun disconnect() {
        Log.d("disconnect","disconnect")
    }
}