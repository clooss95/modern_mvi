package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import com.bonacode.modernmvi.core.PartialState
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

sealed class DogDetailsPartialState : PartialState<DogDetailsViewState, DogDetailsViewEffect> {

    object Loading : DogDetailsPartialState() {
        override fun reduce(previousState: DogDetailsViewState): DogDetailsViewState {
            return previousState.copy(showProgressBar = true)
        }
    }

    data class Error(private val value: Throwable) : DogDetailsPartialState() {
        override fun reduce(previousState: DogDetailsViewState): DogDetailsViewState {
            return previousState.copy(error = value, showProgressBar = false)
        }
    }

    data class DataLoaded(private val dog: Dog) : DogDetailsPartialState() {
        override fun reduce(previousState: DogDetailsViewState): DogDetailsViewState {
            return previousState.copy(dog = dog, showProgressBar = false, error = null)
        }
    }
}
