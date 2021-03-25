package com.bonacode.modernmvi.presentation.feature.dogs.details

import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.DogDetailsInteractor
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.DogDetailsPartialState
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.DogDetailsPresenter
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.DogDetailsViewState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class DogDetailsPresenterTest {
    private val testScheduler = Schedulers.trampoline()
    private val interactor = mock(DogDetailsInteractor::class.java)
    private val presenter = DogDetailsPresenter(
        mainThread = testScheduler,
        interactor = interactor
    )
    private val viewRobot = DogDetailsViewRobot(presenter)
    private val dogId = 1L
    private val dog = Dog(
        id = dogId,
        name = "Dog1",
        breed = "Breed1",
        imageUrl = "url1"
    )
    private val error = IOException("Loading dog failed.")

    @Test
    fun `when load dog details and dog object returned then proper view states emitted`() {
        whenever(interactor.loadDogDetails(dogId)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogDetailsPartialState.Loading,
                    DogDetailsPartialState.DataLoaded(dog)
                )
            )
        )

        viewRobot.test {
            viewRobot.loadDogDetails(dogId)
        }
        viewRobot.assertViewStates(
            DogDetailsViewState(),
            DogDetailsViewState(showProgressBar = true),
            DogDetailsViewState(showProgressBar = false, dog = dog)
        )
    }

    @Test
    fun `when load dog details and error returned then proper view states emitted`() {
        whenever(interactor.loadDogDetails(dogId)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogDetailsPartialState.Loading,
                    DogDetailsPartialState.Error(error)
                )
            )
        )

        viewRobot.test {
            viewRobot.loadDogDetails(dogId)
        }
        viewRobot.assertViewStates(
            DogDetailsViewState(),
            DogDetailsViewState(showProgressBar = true),
            DogDetailsViewState(showProgressBar = false, error = error)
        )
    }

    @Test
    fun `when load dog details then no view effects emitted`() {
        whenever(interactor.loadDogDetails(dogId)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogDetailsPartialState.Loading,
                    DogDetailsPartialState.DataLoaded(dog)
                )
            )
        )

        viewRobot.test {
            viewRobot.loadDogDetails(dogId)
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when load dog details then proper interactor methods called`() {
        whenever(interactor.loadDogDetails(dogId)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogDetailsPartialState.Loading,
                    DogDetailsPartialState.DataLoaded(dog)
                )
            )
        )

        viewRobot.test {
            viewRobot.loadDogDetails(dogId)
        }

        verify(interactor).loadDogDetails(dogId)
        verifyNoMoreInteractions(interactor)
    }
}