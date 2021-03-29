package com.bonacode.modernmvi.presentation.feature.dogs.details

import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.GetDogDetails
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.DogDetailsInteractor
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.DogDetailsPartialState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class DogDetailsInteractorTest {
    private val getDogDetails = mock(GetDogDetails::class.java)
    private val interactor = DogDetailsInteractor(
        getDogDetails = getDogDetails
    )
    private val dogId = 1L
    private val dog = Dog(
        id = dogId,
        name = "Dog1",
        breed = "Breed1",
        imageUrl = "url1"
    )
    private val error = IOException("Loading dog failed.")

    @Test
    fun `when load dog details and dog object returned then proper partial states emitted`() {
        whenever(getDogDetails.buildObservable(GetDogDetails.Params(dogId))).doReturn(
            Observable.just(
                dog
            )
        )

        interactor.loadDogDetails(dogId)
            .test()
            .assertValues(
                DogDetailsPartialState.Loading,
                DogDetailsPartialState.DataLoaded(dog)
            )
    }

    @Test
    fun `when load dog details and error returned then proper partial states emitted`() {
        whenever(getDogDetails.buildObservable(GetDogDetails.Params(dogId))).doReturn(
            Observable.error(
                error
            )
        )

        interactor.loadDogDetails(dogId)
            .test()
            .assertValues(
                DogDetailsPartialState.Loading,
                DogDetailsPartialState.Error(error)
            )
    }

    @Test
    fun `when  load dog details then proper usecase methods called`() {
        whenever(getDogDetails.buildObservable(GetDogDetails.Params(dogId))).doReturn(
            Observable.just(
                dog
            )
        )

        interactor.loadDogDetails(dogId).test()

        verify(getDogDetails).buildObservable(GetDogDetails.Params(dogId))
        verifyNoMoreInteractions(getDogDetails)
    }
}
