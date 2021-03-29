package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.ViewEffect
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

sealed class DogListViewEffect : ViewEffect {
    object NavigateToCreate : DogListViewEffect()
    data class NavigateToDogDetails(val dog: Dog) : DogListViewEffect()
}
