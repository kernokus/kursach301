package com.example.data.di

import com.example.data.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class constantsModule {
    @Provides
    @Singleton
    fun provideConstantsModule(): Constants {
        return Constants
    }
}