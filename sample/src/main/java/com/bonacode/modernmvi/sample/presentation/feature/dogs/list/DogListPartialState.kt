package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.PartialState

sealed class DogListPartialState : PartialState<DogListViewState, DogListViewEffect> {
    object NavigateToDogDetails : DogListPartialState() {
        override fun mapToViewEffect(): DogListViewEffect {
            return DogListViewEffect.NavigateToDogDetails
        }
    }
}
