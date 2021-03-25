package com.bonacode.modernmvi.feature.dogs.list

import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.FindAllDogs
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListInteractor
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListPartialState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class DogListInteractorTest {
    private val findAllDogs = mock(FindAllDogs::class.java)
    private val interactor = DogListInteractor(
        findAllDogs = findAllDogs
    )

    private val dogList = listOf(
        Dog(
            id = 1L,
            name = "Dog1",
            breed = "Breed1",
            imageUrl = "url1"
        ),
        Dog(
            id = 2L,
            name = "Dog2",
            breed = "Breed2",
            imageUrl = "url2"
        )
    )

    private val error = IOException("Loading dog list failed.")

    @Test
    fun `when find all dogs and dog list returned then proper partial states emitted`() {
        whenever(findAllDogs.buildObservable(Unit)).doReturn(Observable.just(dogList))

        interactor.refreshDogList()
            .test()
            .assertValues(
                DogListPartialState.DogListLoading,
                DogListPartialState.DogListUpdated(dogList)
            )
    }

    @Test
    fun `when find all dogs and error returned then proper partial states emitted`() {
        whenever(findAllDogs.buildObservable(Unit)).doReturn(Observable.error(error))

        interactor.refreshDogList()
            .test()
            .assertValues(
                DogListPartialState.DogListLoading,
                DogListPartialState.Error(error)
            )
    }

    @Test
    fun `when find all dogs then proper usecase methods called`() {
        whenever(findAllDogs.buildObservable(Unit)).doReturn(Observable.just(dogList))

        interactor.refreshDogList().test()

        verify(findAllDogs).buildObservable(Unit)
        verifyNoMoreInteractions(findAllDogs)
    }
}