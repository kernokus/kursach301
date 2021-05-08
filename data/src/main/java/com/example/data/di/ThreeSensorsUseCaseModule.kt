package com.example.data.di

import com.example.data.repoImpl.DHT11RepoImpl
import com.example.data.repoImpl.ThreeSensorsRepoImpl
import com.example.domain.repositories.I_BleUseCase3Sensors
import com.example.domain.repositories.I_MainBleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ThreeSensorsUseCaseModule {
    @Binds
    abstract fun bindInterface(repoImpl: ThreeSensorsRepoImpl): I_BleUseCase3Sensors //аргумент - где реализован интерфейс
}