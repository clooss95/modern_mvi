package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import com.bonacode.modernmvi.core.ViewState
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

data class DogDetailsViewState(
    val dog: Dog? = null,
    val showProgressBar: Boolean = false,
    val error: Throwable? = null
) : ViewState
