package com.bonacode.modernmvi.sample.presentation.di

import com.bonacode.modernmvi.sample.data.feature.dogs.repository.DogsRepository
import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindDogsDataSource(impl: DogsRepository): DogsDataSource
}