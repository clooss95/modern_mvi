package com.bonacode.modernmvi.domain.feature.dogs.usecase

import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.FindAllDogs
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import org.mockito.Mockito.mock

class FindAllDogsTest {
    private val dataSource = mock(DogsDataSource::class.java)
    private val useCase = FindAllDogs(
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
    private val dogList = listOf(dog)

    @Test
    fun `when find all then proper value returned`() {
        whenever(dataSource.findAll()).doReturn(Observable.just(dogList))

        useCase.buildObservable(Unit)
            .test()
            .assertValue(dogList)
    }

    @Test
    fun `when find all then proper data source methods called`() {
        whenever(dataSource.findAll()).doReturn(Observable.just(dogList))

        useCase.buildObservable(Unit).test()

        verify(dataSource).findAll()
        verifyNoMoreInteractions(dataSource)
    }
}
