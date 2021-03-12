package com.bonacode.modernmvi.sample.feature.second.fragmentfirst

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.feature.di.SchedulersModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class FirstPresenter @ViewModelInject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler
) : Presenter<FirstViewState, FirstView, FirstPartialState, FirstIntent, FirstViewEffect>(mainThread) {
    override val defaultViewState: FirstViewState
        get() = FirstViewState()

    override fun intentToPartialState(intent: FirstIntent): Observable<FirstPartialState> =
        when (intent) {
            is FirstIntent.NavigateForward -> Observable.just(FirstPartialState.NavigateForward)
        }

}