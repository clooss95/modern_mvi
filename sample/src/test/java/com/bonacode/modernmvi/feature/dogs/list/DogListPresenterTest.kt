package com.bonacode.modernmvi.feature.dogs.list

import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class DogListPresenterTest {
    private val testScheduler = Schedulers.trampoline()
    private val interactor = mock(DogListInteractor::class.java)
    private val presenter = DogListPresenter(mainThread = testScheduler, interactor = interactor)
    private val viewRobot = DogListViewRobot(presenter)

    private val dog = Dog(
        id = 1L,
        name = "Dog1",
        breed = "Breed1",
        imageUrl = "url1"
    )
    private val dogList = listOf(dog)

    @Test
    fun `when navigate to create then proper view effect emitted`() {
        viewRobot.test {
            viewRobot.navigateToCreate()
        }
        viewRobot.assertViewEffects(DogListViewEffect.NavigateToCreate)
    }

    @Test
    fun `when navigate to create then only default view state emitted`() {
        viewRobot.test {
            viewRobot.navigateToCreate()
        }
        viewRobot.assertViewStates(DogListViewState())
    }

    @Test
    fun `when navigate to details then proper view effect emitted`() {
        viewRobot.test {
            viewRobot.navigateToDogDetails(dog)
        }
        viewRobot.assertViewEffects(DogListViewEffect.NavigateToDogDetails(dog))
    }

    @Test
    fun `when navigate to details then only default view state emitted`() {
        viewRobot.test {
            viewRobot.navigateToDogDetails(dog)
        }
        viewRobot.assertViewStates(DogListViewState())
    }

    @Test
    fun `when refresh dog list then no view effects emitted`(){
        whenever(interactor.refreshDogList()).doReturn(
            Observable.fromIterable(
                listOf(
                    DogListPartialState.DogListLoading,
                    DogListPartialState.DogListUpdated(dogList)
                )
            )
        )

        viewRobot.test {
            viewRobot.refreshDogList()
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when refresh dog list and dod list returned then proper view states emitted`() {
        whenever(interactor.refreshDogList()).doReturn(
            Observable.fromIterable(
                listOf(
                    DogListPartialState.DogListLoading,
                    DogListPartialState.DogListUpdated(dogList)
                )
            )
        )

        viewRobot.test {
            viewRobot.refreshDogList()
        }
        viewRobot.assertViewStates(
            DogListViewState(),
            DogListViewState(showProgressBar = true),
            DogListViewState(showProgressBar = false, dogList = dogList)
        )
    }

    @Test
    fun `when refresh dog list and error returned then proper view states emitted`() {
        val error = IOException("Loading dog list failed.")
        whenever(interactor.refreshDogList()).doReturn(
            Observable.fromIterable(
                listOf(
                    DogListPartialState.DogListLoading,
                    DogListPartialState.Error(error)
                )
            )
        )

        viewRobot.test {
            viewRobot.refreshDogList()
        }
        viewRobot.assertViewStates(
            DogListViewState(),
            DogListViewState(showProgressBar = true),
            DogListViewState(showProgressBar = false, error = error)
        )
    }
}