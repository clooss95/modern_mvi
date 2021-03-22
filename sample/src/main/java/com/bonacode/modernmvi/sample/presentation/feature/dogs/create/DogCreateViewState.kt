package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import com.bonacode.modernmvi.core.ViewState

data class DogCreateViewState(
    val name: String? = null,
    val breed: String? = null,
    val imageUrl: String? = null,
    val nameError: Int? = null,
    val breedError: Int? = null,
    val showProgressBar: Boolean = false,
    val error: Throwable? = null
) : ViewState {
    val formValid: Boolean =
        name != null && name.isNotEmpty() && breed != null && breed.isNotEmpty()
}