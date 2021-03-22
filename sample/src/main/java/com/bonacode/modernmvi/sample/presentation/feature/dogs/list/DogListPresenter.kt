package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class DogListPresenter @ViewModelInject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler
) : Presenter<DogListViewState, DogListView, DogListPartialState, DogListIntent, DogListViewEffect>(mainThread) {
    override val defaultViewState: DogListViewState
        get() = DogListViewState()

    override fun intentToPartialState(intent: DogListIntent): Observable<DogListPartialState> =
        when (intent) {
            is DogListIntent.NavigateToDogDetails -> Observable.just(DogListPartialState.NavigateToDogDetails)
        }

}