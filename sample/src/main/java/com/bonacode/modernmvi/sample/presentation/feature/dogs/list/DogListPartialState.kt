package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.PartialState
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

sealed class DogListPartialState : PartialState<DogListViewState, DogListViewEffect> {
    object DogListLoading : DogListPartialState() {
        override fun reduce(previousState: DogListViewState): DogListViewState {
            return previousState.copy(showProgressBar = true, error = null)
        }
    }

    data class DogListUpdated(
        private val dogList: List<Dog>
    ) : DogListPartialState() {
        override fun reduce(previousState: DogListViewState): DogListViewState {
            return previousState.copy(
                dogList = dogList,
                showProgressBar = false
            )
        }
    }

    data class Error(
        val value: Throwable
    ) : DogListPartialState() {
        override fun reduce(previousState: DogListViewState): DogListViewState {
            return previousState.copy(
                error = value,
                showProgressBar = false
            )
        }
    }

    data class NavigateToDogDetails(
        private val dog: Dog
    ) : DogListPartialState() {
        override fun mapToViewEffect(): DogListViewEffect {
            return DogListViewEffect.NavigateToDogDetails(dog)
        }
    }

    object NavigateToCreate: DogListPartialState(){
        override fun mapToViewEffect(): DogListViewEffect {
            return DogListViewEffect.NavigateToCreate
        }
    }
}
