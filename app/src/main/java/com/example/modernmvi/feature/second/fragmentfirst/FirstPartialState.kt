package com.example.modernmvi.feature.second.fragmentfirst

import com.example.modernmvi.base.PartialState

sealed class FirstPartialState : PartialState<FirstViewState, FirstViewEffect> {
    object NavigateForward : FirstPartialState() {
        override fun mapToViewEffect(): FirstViewEffect {
            return FirstViewEffect.NavigateForward
        }
    }
}
