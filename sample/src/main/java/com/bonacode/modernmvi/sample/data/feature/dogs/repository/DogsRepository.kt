package com.bonacode.modernmvi.sample.data.feature.dogs.repository

import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepository @Inject constructor() : DogsDataSource {

    private val dogs = mutableListOf(
        Dog(
            id = 1L,
            name = "Kazan",
            breed = "Owczarek Niemiecki",
            imageUrl = "https://thumbs.img-sprzedajemy.pl/1000x901c/bc/23/4d/owczarek-niemiecki-dlugowlosy-zkwp-fci-tuchola-509596958.jpg"
        ),
        Dog(
            id = 2L,
            name = "Rami",
            breed = "Labrador",
            imageUrl = "https://static.fajnyzwierzak.pl/media/uploads/media_image/original/wpis/284/labrador-retriever.jpg"
        )
    )

    override fun findAll(): Observable<List<Dog>> =
        Observable.just(dogs.toList())
            .delay(1500, TimeUnit.MILLISECONDS)
}