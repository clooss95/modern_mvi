package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

class DogCreatePresenter @ViewModelInject constructor(
    @Named(SchedulersModule.MAIN_THREAD) mainThread: Scheduler,
    private val interactor: DogCreateInteractor
) :
    Presenter<DogCreateViewState, DogCreateView, DogCreatePartialState, DogCreateIntent, DogCreateViewEffect>(
        mainThread
    ) {
    override val defaultViewState: DogCreateViewState
        get() = DogCreateViewState()

    override fun intentToPartialState(intent: DogCreateIntent): Observable<DogCreatePartialState> =
        when (intent) {
            is DogCreateIntent.NameChanged -> Observable.just(
                DogCreatePartialState.NameChanged(
                    intent.name
                )
            )
            is DogCreateIntent.BreedChanged -> Observable.just(
                DogCreatePartialState.BreedChanged(
                    intent.breed
                )
            )
            is DogCreateIntent.ImageUrlChanged -> Observable.just(
                DogCreatePartialState.ImageUrlChanged(
                    intent.imageUrl
                )
            )
            is DogCreateIntent.Save -> with(getViewState()) {
                interactor.createDog(name, breed, imageUrl)
            }
        }
}