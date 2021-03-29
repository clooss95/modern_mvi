package com.bonacode.modernmvi.sample.data.feature.dogs.repository

import com.bonacode.modernmvi.sample.data.feature.dogs.mapper.DogsMapper
import com.bonacode.modernmvi.sample.data.network.DogsApiService
import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepository @Inject constructor(
    private val apiService: DogsApiService,
    private val mapper: DogsMapper
) : DogsDataSource {

    override fun findAll(): Observable<List<Dog>> =
        apiService.findAll()
            .map { mapper.transform(it) }

    override fun getDogById(id: Long): Observable<Dog> =
        apiService.getDogById(id)
            .map { mapper.transform(it) }

    override fun addDog(name: String, breed: String, imageUrl: String?): Completable =
        apiService.addDog(name, breed, imageUrl)
}
