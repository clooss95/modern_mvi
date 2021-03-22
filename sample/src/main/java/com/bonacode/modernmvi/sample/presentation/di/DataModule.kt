package com.bonacode.modernmvi.sample.presentation.di

import com.bonacode.modernmvi.sample.data.feature.dogs.repository.DogsRepository
import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindDogsDataSource(impl: DogsRepository): DogsDataSource
}