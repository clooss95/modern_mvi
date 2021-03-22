package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.ViewEffect

sealed class DogListViewEffect : ViewEffect {
    object NavigateToDogDetails : DogListViewEffect()
}
