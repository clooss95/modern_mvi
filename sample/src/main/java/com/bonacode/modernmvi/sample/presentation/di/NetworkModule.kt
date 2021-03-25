package com.bonacode.modernmvi.sample.presentation.di

import com.bonacode.modernmvi.sample.data.network.DogsApiService
import com.bonacode.modernmvi.sample.data.network.MockedDogApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface NetworkModule {
    @Singleton
    @Binds
    fun bindsDogApiService(impl: MockedDogApiService): DogsApiService
}