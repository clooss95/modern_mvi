package com.bonacode.modernmvi.data.feature.dogs.mapper

import com.bonacode.modernmvi.sample.data.feature.dogs.dto.DogDto
import com.bonacode.modernmvi.sample.data.feature.dogs.mapper.DogsMapper
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import junit.framework.Assert.assertEquals
import org.junit.Test

class DogMapperTest {
    private val mapper = DogsMapper()
    private val dto = DogDto(
        1L,
        "Kazan",
        "German shepherd",
        "image1"
    )
    private val model = Dog(
        1L,
        "Kazan",
        "German shepherd",
        "image1"
    )

    @Test
    fun `when given input then proper mapping`() {
        assertEquals(model, mapper.transform(dto))
    }

    @Test
    fun `when given input then proper reverse mapping`() {
        assertEquals(dto, mapper.reverse(model))
    }
}
