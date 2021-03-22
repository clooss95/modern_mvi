package com.bonacode.modernmvi.sample.presentation.feature.counter

import com.bonacode.modernmvi.core.PartialState

sealed class CounterPartialState : PartialState<CounterViewState, CounterViewEffect> {
    object Increase : CounterPartialState() {
        override fun reduce(previousState: CounterViewState): CounterViewState {
            return previousState.copy(counterValue = previousState.counterValue + 1)
        }
    }

    object Decrease : CounterPartialState() {
        override fun reduce(previousState: CounterViewState): CounterViewState {
            return previousState.copy(counterValue = previousState.counterValue - 1)
        }
    }

    object NavigateToSecondScreen : CounterPartialState() {
        override fun mapToViewEffect(): CounterViewEffect {
            return CounterViewEffect.NavigateToSecondScreen
        }
    }
}
