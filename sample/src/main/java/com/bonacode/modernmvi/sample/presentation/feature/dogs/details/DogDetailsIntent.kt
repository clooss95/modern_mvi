package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import com.bonacode.modernmvi.core.Intent

sealed class DogDetailsIntent : Intent {
    data class LoadDogDetails(val dogId: Long) : DogDetailsIntent()
}
