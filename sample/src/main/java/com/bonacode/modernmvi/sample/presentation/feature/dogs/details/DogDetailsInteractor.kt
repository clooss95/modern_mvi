package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.GetDogDetails
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DogDetailsInteractor @Inject constructor(
    private val getDogDetails: GetDogDetails
) {
    fun loadDogDetails(id: Long): Observable<DogDetailsPartialState> =
        getDogDetails.buildObservable(GetDogDetails.Params(id))
            .map <DogDetailsPartialState>{ DogDetailsPartialState.DataLoaded(it) }
            .onErrorResumeNext { Observable.just<DogDetailsPartialState>(DogDetailsPartialState.Error(it)) }
            .startWith(Observable.just<DogDetailsPartialState>(DogDetailsPartialState.Loading))
}