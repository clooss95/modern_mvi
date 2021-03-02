package com.example.modernmvi.feature.main

import androidx.hilt.lifecycle.ViewModelInject
import com.example.modernmvi.base.Presenter
import io.reactivex.rxjava3.core.Observable

class MainPresenter @ViewModelInject constructor() :
    Presenter<MainViewState, MainView, MainPartialState, MainIntent, MainViewEffect>() {
    override val defaultViewState: MainViewState
        get() = MainViewState()

    override fun intentToPartialState(intent: MainIntent): Observable<MainPartialState> =
        when (intent) {
            is MainIntent.Increase -> Observable.just(MainPartialState.Increase)
            is MainIntent.Decrease -> Observable.just(MainPartialState.Decrease)
            is MainIntent.NavigateToSecondScreen -> Observable.just(MainPartialState.NavigateToSecondScreen)
        }
}