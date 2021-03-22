package com.bonacode.modernmvi.sample.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {
    const val MAIN_THREAD = "mainThread"

    @Provides
    @Named(MAIN_THREAD)
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}