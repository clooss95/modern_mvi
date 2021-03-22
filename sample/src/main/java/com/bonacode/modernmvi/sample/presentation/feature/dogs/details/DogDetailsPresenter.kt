package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DogDetailsPresenter @Inject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler,
    private val interactor: DogDetailsInteractor
) : Presenter<DogDetailsViewState, DogDetailsView, DogDetailsPartialState, DogDetailsIntent, DogDetailsViewEffect>(mainThread) {
    override val defaultViewState: DogDetailsViewState
        get() = DogDetailsViewState()

    override fun intentToPartialState(intent: DogDetailsIntent): Observable<DogDetailsPartialState> =
        when(intent){
            is DogDetailsIntent.LoadDogDetails -> interactor.loadDogDetails(intent.dogId)
        }

}