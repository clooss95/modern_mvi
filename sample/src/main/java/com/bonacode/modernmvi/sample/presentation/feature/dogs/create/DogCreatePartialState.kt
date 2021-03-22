package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.PartialState

sealed class DogCreatePartialState : PartialState<DogCreateViewState, DogCreateViewEffect> {
    data class NameChanged(private val name: String) : DogCreatePartialState() {
        override fun reduce(previousState: DogCreateViewState): DogCreateViewState {
            return previousState.copy(
                name = name,
                nameError = when{
                    name.isEmpty() -> R.string.dog_create_name_error
                    else -> null
                }
            )
        }
    }

    data class BreedChanged(private val breed: String) : DogCreatePartialState() {
        override fun reduce(previousState: DogCreateViewState): DogCreateViewState {
            return previousState.copy(
                breed = breed,
                breedError = when{
                    breed.isEmpty() -> R.string.dog_create_breed_error
                    else -> null
                }
            )
        }
    }

    data class ImageUrlChanged(private val imageUrl: String) : DogCreatePartialState() {
        override fun reduce(previousState: DogCreateViewState): DogCreateViewState {
            return previousState.copy(
                imageUrl = imageUrl
            )
        }
    }

    object CreateInProgress : DogCreatePartialState() {
        override fun reduce(previousState: DogCreateViewState): DogCreateViewState {
            return previousState.copy(showProgressBar = true, error = null)
        }
    }

    data class Error(private val value: Throwable) : DogCreatePartialState() {
        override fun reduce(previousState: DogCreateViewState): DogCreateViewState {
            return previousState.copy(error = value)
        }
    }

    object Created : DogCreatePartialState() {
        override fun reduce(previousState: DogCreateViewState): DogCreateViewState {
            return previousState.copy(showProgressBar = false)
        }

        override fun mapToViewEffect(): DogCreateViewEffect {
            return DogCreateViewEffect.NavigateBack
        }
    }

}
