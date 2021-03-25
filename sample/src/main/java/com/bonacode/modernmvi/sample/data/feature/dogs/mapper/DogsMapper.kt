package com.bonacode.modernmvi.sample.data.feature.dogs.mapper

import com.bonacode.modernmvi.sample.data.BaseMapper
import com.bonacode.modernmvi.sample.data.feature.dogs.dto.DogDto
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import javax.inject.Inject

class DogsMapper @Inject constructor(): BaseMapper<DogDto, Dog> {
    override fun transform(input: DogDto): Dog = with(input) {
        Dog(
            id = id,
            name = name,
            breed = breed,
            imageUrl = imageUrl
        )
    }

    override fun reverse(input: Dog): DogDto = with(input) {
        DogDto(
            id = id,
            name = name,
            breed = breed,
            imageUrl = imageUrl
        )
    }
}