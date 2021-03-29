package com.bonacode.modernmvi.domain.feature.dogs.usecase

import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.usecase.AddDog
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import org.junit.Test
import org.mockito.Mockito.mock

class AddDogTest {
    private val dataSource = mock(DogsDataSource::class.java)
    private val useCase = AddDog(
        dataSource = dataSource
    )
    private val dogName = "name"
    private val dogBreed = "breed"
    private val imageUrl = "imageUrl"

    @Test
    fun `when add dog then success`() {
        whenever(dataSource.addDog(dogName, dogBreed, imageUrl)).doReturn(Completable.complete())

        useCase.buildCompletable(AddDog.Params(dogName, dogBreed, imageUrl))
            .test()
            .assertComplete()
    }

    @Test
    fun `when add dog then proper data source methods called`() {
        whenever(dataSource.addDog(dogName, dogBreed, imageUrl)).doReturn(Completable.complete())

        useCase.buildCompletable(AddDog.Params(dogName, dogBreed, imageUrl)).test()

        verify(dataSource).addDog(dogName, dogBreed, imageUrl)
        verifyNoMoreInteractions(dataSource)
    }
}
