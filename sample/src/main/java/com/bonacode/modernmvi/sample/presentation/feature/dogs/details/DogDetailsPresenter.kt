package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class DogDetailsPresenter @ViewModelInject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler
) : Presenter<DogDetailsViewState, DogDetailsView, DogDetailsPartialState, DogDetailsIntent, DogDetailsViewEffect>(mainThread) {
    override val defaultViewState: DogDetailsViewState
        get() = DogDetailsViewState()

    override fun intentToPartialState(intent: DogDetailsIntent): Observable<DogDetailsPartialState> =
        Observable.never()

}