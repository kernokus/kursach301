package com.example.kursach301.di

import android.content.Context
import com.example.kursach301.Utilities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class UtilitiesModule {
    @Provides
    @Singleton
    fun provideUtilitiesModule(@ApplicationContext context: Context): Utilities {
        return Utilities()
    }
}