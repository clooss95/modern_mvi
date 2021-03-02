package com.example.modernmvi.feature.main

import com.example.modernmvi.base.PartialState

sealed class MainPartialState : PartialState<MainViewState, MainViewEffect> {
    object Increase : MainPartialState() {
        override fun reduce(previousState: MainViewState): MainViewState {
            return previousState.copy(counterValue = previousState.counterValue + 1)
        }
    }

    object Decrease : MainPartialState() {
        override fun reduce(previousState: MainViewState): MainViewState {
            return previousState.copy(counterValue = previousState.counterValue - 1)
        }
    }

    object NavigateToSecondScreen : MainPartialState() {
        override fun mapToViewEffect(): MainViewEffect {
            return MainViewEffect.NavigateToSecondScreen
        }
    }
}
