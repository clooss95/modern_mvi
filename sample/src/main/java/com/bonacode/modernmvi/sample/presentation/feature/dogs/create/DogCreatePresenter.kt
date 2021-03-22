package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import com.bonacode.modernmvi.core.Presenter
import com.bonacode.modernmvi.sample.presentation.di.SchedulersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DogCreatePresenter @Inject constructor(
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