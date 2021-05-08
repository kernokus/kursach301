package com.example.data.di

import com.example.data.repoImpl.DHT11RepoImpl
import com.example.domain.repositories.I_MainBleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class DHT11UseCaseModule {
    @Binds
    abstract fun bindInterface(repoImpl: DHT11RepoImpl): I_MainBleUseCase //аргумент - где реализован интерфейс
}


