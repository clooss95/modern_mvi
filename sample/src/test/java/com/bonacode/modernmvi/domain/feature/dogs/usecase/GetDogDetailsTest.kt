package com.bonacode.modernmvi.domain.feature.dogs.usecase

import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.AddDog
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.FindAllDogs
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.GetDogDetails
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import org.mockito.Mockito.mock

class GetDogDetailsTest {
    private val dataSource = mock(DogsDataSource::class.java)
    private val useCase = GetDogDetails(
        dataSource = dataSource
    )
    private val dogId = 1L
    private val dogName = "name"
    private val dogBreed = "breed"
    private val imageUrl = "imageUrl"
    private val dog = Dog(
        dogId,
        dogName,
        dogBreed,
        imageUrl
    )

    @Test
    fun `when get dog details then proper value returned`() {
        whenever(dataSource.getDogById(dogId)).doReturn(Observable.just(dog))

        useCase.buildObservable(GetDogDetails.Params(dogId))
            .test()
            .assertValue(dog)
    }

    @Test
    fun `when get dog details then proper data source methods called`() {
        whenever(dataSource.getDogById(dogId)).doReturn(Observable.just(dog))

        useCase.buildObservable(GetDogDetails.Params(dogId)).test()

        verify(dataSource).getDogById(dogId)
        verifyNoMoreInteractions(dataSource)
    }
}