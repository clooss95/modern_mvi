package com.bonacode.modernmvi.sample.feature.main

import com.bonacode.modernmvi.core.PartialState

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
