package com.bonacode.modernmvi.sample.domain.feature.dogs.datasource

import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import io.reactivex.rxjava3.core.Observable

interface DogsDataSource {
    fun findAll(): Observable<List<Dog>>
    fun getDogById(id: Long): Observable<Dog>
}