package com.bonacode.modernmvi.sample.data.feature.dogs.repository

import com.bonacode.modernmvi.sample.domain.feature.dogs.datasource.DogsDataSource
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.io.IOException
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

    override fun getDogById(id: Long): Observable<Dog> {
        val dog = dogs.find { it.id == id }

        val observable = if (dog != null) {
            Observable.just(dog)
        } else {
            Observable.error(IOException("Not found"))
        }

        return observable.delay(1500, TimeUnit.MILLISECONDS)
    }

    override fun addDog(name: String, breed: String, imageUrl: String?): Completable {
        val prevId: Long = dogs.map { it.id }.maxOrNull() ?: 1L

        dogs.add(
            Dog(
                id = prevId + 1,
                name = name,
                breed = breed,
                imageUrl = imageUrl?.takeIf { it.isNotBlank() }
                    ?: "https://static.fajnyzwierzak.pl/media/uploads/media_image/original/wpis/284/labrador-retriever.jpg"
            )
        )

        return Completable.complete().delay(1500, TimeUnit.MILLISECONDS)
    }

}