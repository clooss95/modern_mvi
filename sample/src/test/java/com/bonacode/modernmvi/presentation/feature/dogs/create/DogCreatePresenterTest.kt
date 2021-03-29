package com.bonacode.modernmvi.presentation.feature.dogs.create

import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateInteractor
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreatePartialState
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreatePresenter
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateViewEffect
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateViewState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class DogCreatePresenterTest {
    private val testScheduler = Schedulers.trampoline()
    private val interactor = mock(DogCreateInteractor::class.java)
    private val presenter = DogCreatePresenter(testScheduler, interactor)
    private val viewRobot = DogCreateViewRobot(presenter)
    private val name = "name"
    private val invalidName = "  "
    private val breed = "breed"
    private val invalidBreed = "  "
    private val imageUrl = "imageUrl"
    private val error = IOException("Create dog failed.")

    @Test
    fun `when new, valid name set then proper view states emitted`() {
        viewRobot.test {
            viewRobot.nameChanged(name)
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(name = name)
        )
    }

    @Test
    fun `when new, invalid name set then proper view states emitted`() {
        viewRobot.test {
            viewRobot.nameChanged(invalidName)
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(name = invalidName, nameError = R.string.dog_create_name_error)
        )
    }

    @Test
    fun `when new name set then no view effects emitted`() {
        viewRobot.test {
            viewRobot.nameChanged(name)
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when new name set then no interactor methods called`() {
        viewRobot.test {
            viewRobot.nameChanged(name)
        }
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `when new, valid breed set then proper view states emitted`() {
        viewRobot.test {
            viewRobot.breedChanged(breed)
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(breed = breed)
        )
    }

    @Test
    fun `when new, invalid breed set then proper view states emitted`() {
        viewRobot.test {
            viewRobot.breedChanged(invalidBreed)
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(breed = invalidBreed, breedError = R.string.dog_create_breed_error)
        )
    }

    @Test
    fun `when new breed set then no view effects emitted`() {
        viewRobot.test {
            viewRobot.breedChanged(breed)
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when new breed set then no interactor methods called`() {
        viewRobot.test {
            viewRobot.breedChanged(breed)
        }
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `when new image url set then proper view states emitted`() {
        viewRobot.test {
            viewRobot.imageUlrChanged(imageUrl)
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(imageUrl = imageUrl)
        )
    }

    @Test
    fun `when new image url set then no view effects emitted`() {
        viewRobot.test {
            viewRobot.imageUlrChanged(imageUrl)
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when new image url then no interactor methods called`() {
        viewRobot.test {
            viewRobot.imageUlrChanged(imageUrl)
        }
        verifyZeroInteractions(interactor)
    }

    @Test
    fun `when create dog and create dog success then proper view states emitted`() {
        whenever(interactor.createDog(name, breed, imageUrl)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogCreatePartialState.CreateInProgress,
                    DogCreatePartialState.Created
                )
            )
        )

        viewRobot.test {
            viewRobot.nameChanged(name)
            viewRobot.breedChanged(breed)
            viewRobot.imageUlrChanged(imageUrl)
            viewRobot.saveClicked()
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(name = name),
            DogCreateViewState(name = name, breed = breed),
            DogCreateViewState(name = name, breed = breed, imageUrl = imageUrl),
            DogCreateViewState(
                name = name,
                breed = breed,
                imageUrl = imageUrl,
                showProgressBar = true
            ),
            DogCreateViewState(
                name = name,
                breed = breed,
                imageUrl = imageUrl,
                showProgressBar = false
            )
        )
    }

    @Test
    fun `when create dog and create dog failed then proper view states emitted`() {
        whenever(interactor.createDog(name, breed, imageUrl)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogCreatePartialState.CreateInProgress,
                    DogCreatePartialState.Error(error)
                )
            )
        )

        viewRobot.test {
            viewRobot.nameChanged(name)
            viewRobot.breedChanged(breed)
            viewRobot.imageUlrChanged(imageUrl)
            viewRobot.saveClicked()
        }
        viewRobot.assertViewStates(
            DogCreateViewState(),
            DogCreateViewState(name = name),
            DogCreateViewState(name = name, breed = breed),
            DogCreateViewState(name = name, breed = breed, imageUrl = imageUrl),
            DogCreateViewState(name = name, breed = breed, imageUrl = imageUrl, showProgressBar = true),
            DogCreateViewState(name = name, breed = breed, imageUrl = imageUrl, showProgressBar = false, error = error)
        )
    }

    @Test
    fun `when create dog then proper view states emitted`() {
        whenever(interactor.createDog(name, breed, imageUrl)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogCreatePartialState.CreateInProgress,
                    DogCreatePartialState.Created
                )
            )
        )

        viewRobot.test {
            viewRobot.nameChanged(name)
            viewRobot.breedChanged(breed)
            viewRobot.imageUlrChanged(imageUrl)
            viewRobot.saveClicked()
        }
        viewRobot.assertViewEffects(
            DogCreateViewEffect.HideKeyboard,
            DogCreateViewEffect.NavigateBack
        )
    }

    @Test
    fun `when create dog then proper interactor methods called`() {
        whenever(interactor.createDog(name, breed, imageUrl)).doReturn(
            Observable.fromIterable(
                listOf(
                    DogCreatePartialState.CreateInProgress,
                    DogCreatePartialState.Created
                )
            )
        )

        viewRobot.test {
            viewRobot.nameChanged(name)
            viewRobot.breedChanged(breed)
            viewRobot.imageUlrChanged(imageUrl)
            viewRobot.saveClicked()
        }
        verify(interactor).createDog(name, breed, imageUrl)
        verifyNoMoreInteractions(interactor)
    }
}
