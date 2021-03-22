package com.bonacode.modernmvi.sample.domain.feature.dogs.usecase

import com.bonacode.modernmvi.sample.domain.CompletableUseCase
import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddDog @Inject constructor(
    private val dataSource: DogsDataSource
) : CompletableUseCase<AddDog.Params> {
    override fun buildCompletable(params: Params): Completable = with(params) {
        dataSource.addDog(name = name, breed = breed, imageUrl = imageUrl)
    }

    data class Params(
        val name: String,
        val breed: String,
        val imageUrl: String?
    )
}