package com.bonacode.modernmvi.sample.feature.second.fragmentsecond

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.feature.di.SchedulersModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class SecondPresenter @ViewModelInject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler
) : Presenter<SecondViewState, SecondView, SecondPartialState, SecondIntent, SecondViewEffect>(mainThread) {
    override val defaultViewState: SecondViewState
        get() = SecondViewState()

    override fun intentToPartialState(intent: SecondIntent): Observable<SecondPartialState> =
        when (intent) {
            is SecondIntent.NavigateForward -> Observable.just(SecondPartialState.NavigateForward)
        }

}