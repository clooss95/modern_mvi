package com.bonacode.modernmvi.sample.domain.feature.dogs.usecase

import com.bonacode.modernmvi.sample.domain.ObservableUseCase
import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetDogDetails @Inject constructor(
    private val dataSource: DogsDataSource
) : ObservableUseCase<GetDogDetails.Params, Dog> {

    override fun buildObservable(params: Params): Observable<Dog> =
        dataSource.getDogById(params.id)

    data class Params(
        val id: Long
    )
}