package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.FindAllDogs
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DogListInteractor @Inject constructor(
    private val findAllDogs: FindAllDogs
) {
    fun refreshDogList(): Observable<DogListPartialState> =
        findAllDogs.buildObservable(Unit)
            .map<DogListPartialState> { DogListPartialState.DogListUpdated(it) }
            .onErrorResumeNext { Observable.just(DogListPartialState.Error(it)) }
            .startWith(Observable.just(DogListPartialState.DogListLoading))
}