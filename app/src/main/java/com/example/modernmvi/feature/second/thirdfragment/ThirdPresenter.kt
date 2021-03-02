package com.example.modernmvi.feature.second.thirdfragment

import androidx.hilt.lifecycle.ViewModelInject
import com.example.modernmvi.base.Presenter
import com.example.modernmvi.feature.di.SchedulersModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class ThirdPresenter @ViewModelInject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler
) : Presenter<ThirdViewState, ThirdView, ThirdPartialState, ThirdIntent, ThirdViewEffect>(mainThread) {
    override val defaultViewState: ThirdViewState
        get() = ThirdViewState()

    override fun intentToPartialState(intent: ThirdIntent): Observable<ThirdPartialState> =
        when (intent) {
            is ThirdIntent.NavigateForward -> Observable.just(ThirdPartialState.NavigateForward)
        }

}