package com.bonacode.modernmvi.presentation.feature.dogs.create

import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.AddDog
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateInteractor
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreatePartialState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException
import java.lang.IllegalArgumentException

class DogCreateInteractorTest {
    private val addDog = mock(AddDog::class.java)
    private val interactor = DogCreateInteractor(
        addDog = addDog
    )
    private val dogName = "dog name"
    private val breed = "breed"
    private val imageUrl = "url"

    @Test
    fun `when create dog with null name then error returned`() {
        interactor.createDog(null, breed, imageUrl)
            .test()
            .assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `when create dog with null name then no usecase methods called`() {
        interactor.createDog(null, breed, imageUrl).test()
        verifyZeroInteractions(addDog)
    }

    @Test
    fun `when create dog with null bread then error returned`() {
        interactor.createDog(dogName, null, imageUrl)
            .test()
            .assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `when create dog with null bread then no usecase methods called`() {
        interactor.createDog(null, breed, imageUrl).test()
        verifyZeroInteractions(addDog)
    }

    @Test
    fun `when create dog with valid data and create dog success then proper partial states emitted`() {
        whenever(addDog.buildCompletable(AddDog.Params(dogName, breed, imageUrl))).doReturn(
            Completable.complete()
        )

        interactor.createDog(dogName, breed, imageUrl)
            .test()
            .assertValues(
                DogCreatePartialState.CreateInProgress,
                DogCreatePartialState.Created
            )
    }

    @Test
    fun `when create dog with valid data and create dog failed then proper partial states emitted`() {
        val error = IOException("Create dog failed.")

        whenever(addDog.buildCompletable(AddDog.Params(dogName, breed, imageUrl))).doReturn(
            Completable.error(error)
        )

        interactor.createDog(dogName, breed, imageUrl)
            .test()
            .assertValues(
                DogCreatePartialState.CreateInProgress,
                DogCreatePartialState.Error(error)
            )
    }

    @Test
    fun `when create dog then proper usecase methods called`() {
        whenever(addDog.buildCompletable(AddDog.Params(dogName, breed, imageUrl))).doReturn(
            Completable.complete()
        )

        interactor.createDog(dogName, breed, imageUrl).test()

        verify(addDog).buildCompletable(AddDog.Params(dogName, breed, imageUrl))
        verifyZeroInteractions(addDog)
    }
}
