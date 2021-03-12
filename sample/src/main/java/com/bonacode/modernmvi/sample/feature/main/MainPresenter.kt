package com.bonacode.modernmvi.sample.feature.main

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.feature.di.SchedulersModule.MAIN_THREAD
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class MainPresenter @ViewModelInject constructor(
   @Named(MAIN_THREAD) mainThread: Scheduler
) : Presenter<MainViewState, MainView, MainPartialState, MainIntent, MainViewEffect>(mainThread) {
    override val defaultViewState: MainViewState
        get() = MainViewState()

    override fun intentToPartialState(intent: MainIntent): Observable<MainPartialState> =
        when (intent) {
            is MainIntent.Increase -> Observable.just(MainPartialState.Increase)
            is MainIntent.Decrease -> Observable.just(MainPartialState.Decrease)
            is MainIntent.NavigateToSecondScreen -> Observable.just(MainPartialState.NavigateToSecondScreen)
        }
}