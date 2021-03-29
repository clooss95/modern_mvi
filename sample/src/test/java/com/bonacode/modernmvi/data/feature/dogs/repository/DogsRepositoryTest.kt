package com.bonacode.modernmvi.data.feature.dogs.repository

import com.bonacode.modernmvi.sample.data.feature.dogs.dto.DogDto
import com.bonacode.modernmvi.sample.data.feature.dogs.mapper.DogsMapper
import com.bonacode.modernmvi.sample.data.feature.dogs.repository.DogsRepository
import com.bonacode.modernmvi.sample.data.network.DogsApiService
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import org.mockito.Mockito.mock

class DogsRepositoryTest {
    private val apiService = mock(DogsApiService::class.java)
    private val mapper = DogsMapper()
    private val repository = DogsRepository(
        apiService,
        mapper
    )
    private val dogId = 1L
    private val dogName = "name"
    private val dogBreed = "breed"
    private val imageUrl = "imageUrl"

    private val dogDto = DogDto(
        dogId,
        dogName,
        dogBreed,
        imageUrl
    )
    private val dtoList = listOf(dogDto)
    private val dogModel = Dog(
        dogId,
        dogName,
        dogBreed,
        imageUrl
    )
    private val modelList = listOf(dogModel)

    @Test
    fun `when get dog by id then proper value returned`() {
        whenever(apiService.getDogById(dogId)).doReturn(Observable.just(dogDto))

        repository.getDogById(dogId)
            .test()
            .assertValue(dogModel)
    }

    @Test
    fun `when get dog by id then proper api service methods called`() {
        whenever(apiService.getDogById(dogId)).doReturn(Observable.just(dogDto))

        repository.getDogById(dogId).test()

        verify(apiService).getDogById(dogId)
        verifyNoMoreInteractions(apiService)
    }

    @Test
    fun `when find all then proper value returned`() {
        whenever(apiService.findAll()).doReturn(Observable.just(dtoList))

        repository.findAll()
            .test()
            .assertValue(modelList)
    }

    @Test
    fun `when find all then proper api service methods called`() {
        whenever(apiService.findAll()).doReturn(Observable.just(dtoList))

        repository.findAll().test()

        verify(apiService).findAll()
        verifyNoMoreInteractions(apiService)
    }

    @Test
    fun `when add dog then success`() {
        whenever(apiService.addDog(dogName, dogBreed, imageUrl)).doReturn(Completable.complete())

        repository.addDog(dogName, dogBreed, imageUrl)
            .test()
            .assertComplete()
    }

    @Test
    fun `when add dog then proper api service methods called`() {
        whenever(apiService.addDog(dogName, dogBreed, imageUrl)).doReturn(Completable.complete())

        repository.addDog(dogName, dogBreed, imageUrl).test()

        verify(apiService).addDog(dogName, dogBreed, imageUrl)
        verifyNoMoreInteractions(apiService)
    }
}
