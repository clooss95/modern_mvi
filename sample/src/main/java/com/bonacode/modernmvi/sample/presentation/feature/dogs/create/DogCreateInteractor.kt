package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.AddDog
import io.reactivex.rxjava3.core.Observable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class DogCreateInteractor @Inject constructor(
    private val addDog: AddDog
) {
    fun createDog(
        name: String?,
        breed: String?,
        imageUrl: String?
    ): Observable<DogCreatePartialState> =
        if (name == null || breed == null) {
            Observable.error(IllegalArgumentException("Unexpected null name or bread!"))
        } else {
            addDog.buildCompletable(AddDog.Params(name, breed, imageUrl))
                .andThen<DogCreatePartialState>(Observable.just(DogCreatePartialState.Created))
                .onErrorResumeNext { Observable.just(DogCreatePartialState.Error(it)) }
                .startWith(Observable.just(DogCreatePartialState.CreateInProgress))
        }
}
