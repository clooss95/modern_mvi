package com.bonacode.modernmvi.sample.domain.feature.dogs.usecase

import com.bonacode.modernmvi.sample.domain.ObservableUseCase
import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FindAllDogs @Inject constructor(
    private val dataSource: DogsDataSource
) : ObservableUseCase<Unit, List<Dog>> {
    override fun buildObservable(params: Unit): Observable<List<Dog>> =
        dataSource.findAll()
}
