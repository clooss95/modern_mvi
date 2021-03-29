package com.bonacode.modernmvi.sample.data.network

import com.bonacode.modernmvi.sample.data.feature.dogs.dto.DogDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface DogsApiService {
    fun findAll(): Observable<List<DogDto>>

    fun getDogById(id: Long): Observable<DogDto>

    fun addDog(name: String, breed: String, imageUrl: String?): Completable
}

class MockedDogApiService @Inject constructor() : DogsApiService {
    private val dogs = mutableListOf(
        DogDto(
            id = 1L,
            name = "Kazan",
            breed = "Owczarek Niemiecki",
            imageUrl = "https://thumbs.img-sprzedajemy.pl/1000x901c/bc/23/4d/owczarek-niemiecki-dlugowlosy-zkwp-fci-tuchola-509596958.jpg"
        ),
        DogDto(
            id = 2L,
            name = "Rami",
            breed = "Labrador",
            imageUrl = "https://static.fajnyzwierzak.pl/media/uploads/media_image/original/wpis/284/labrador-retriever.jpg"
        )
    )

    override fun findAll(): Observable<List<DogDto>> =
        Observable.just(dogs.toList())
            .delay(1500, TimeUnit.MILLISECONDS)

    override fun getDogById(id: Long): Observable<DogDto> {
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
            DogDto(
                id = prevId + 1,
                name = name,
                breed = breed,
                imageUrl = imageUrl?.takeIf { it.isNotBlank() }
                    ?: DogsImages.value.random()
            )
        )

        return Completable.complete().delay(1500, TimeUnit.MILLISECONDS)
    }
}
