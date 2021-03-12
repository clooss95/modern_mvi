package com.bonacode.modernmvi.sample.feature.second.fragmentfirst

import com.bonacode.modernmvi.core.PartialState

sealed class FirstPartialState : PartialState<FirstViewState, FirstViewEffect> {
    object NavigateForward : FirstPartialState() {
        override fun mapToViewEffect(): FirstViewEffect {
            return FirstViewEffect.NavigateForward
        }
    }
}
