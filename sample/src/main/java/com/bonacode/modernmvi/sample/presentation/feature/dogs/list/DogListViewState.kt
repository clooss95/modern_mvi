package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.ViewState
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

data class DogListViewState(
    val dogList: List<Dog> = emptyList(),
    val showProgressBar: Boolean = false,
    val error: Throwable? = null
) : ViewState