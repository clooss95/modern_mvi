package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DogListPresenter @Inject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler,
    private val interactor: DogListInteractor
) : Presenter<DogListViewState, DogListView, DogListPartialState, DogListIntent, DogListViewEffect>(
    mainThread
) {
    override val defaultViewState: DogListViewState
        get() = DogListViewState()

    override fun intentToPartialState(intent: DogListIntent): Observable<DogListPartialState> =
        when (intent) {
            is DogListIntent.RefreshDogList -> interactor.refreshDogList()
            is DogListIntent.NavigateToDogDetails -> Observable.just(DogListPartialState.NavigateToDogDetails(intent.dog))
            is DogListIntent.NavigateToCreate -> Observable.just(DogListPartialState.NavigateToCreate)
        }

}