package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import com.bonacode.modernmvi.core.Intent

sealed class DogCreateIntent : Intent {
    data class NameChanged(val name: String) : DogCreateIntent()
    data class BreedChanged(val breed: String) : DogCreateIntent()
    data class ImageUrlChanged(val imageUrl: String) : DogCreateIntent()
    object Save : DogCreateIntent()
}
