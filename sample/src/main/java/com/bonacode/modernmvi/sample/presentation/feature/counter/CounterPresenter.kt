package com.bonacode.modernmvi.sample.presentation.feature.counter

import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule.MAIN_THREAD
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CounterPresenter @Inject constructor(
   @Named(MAIN_THREAD) mainThread: Scheduler
) : Presenter<CounterViewState, CounterView, CounterPartialState, CounterIntent, CounterViewEffect>(mainThread) {
    override val defaultViewState: CounterViewState
        get() = CounterViewState()

    override fun intentToPartialState(intent: CounterIntent): Observable<CounterPartialState> =
        when (intent) {
            is CounterIntent.Increase -> Observable.just(CounterPartialState.Increase)
            is CounterIntent.Decrease -> Observable.just(CounterPartialState.Decrease)
            is CounterIntent.NavigateToSecondScreen -> Observable.just(CounterPartialState.NavigateToSecondScreen)
        }
}