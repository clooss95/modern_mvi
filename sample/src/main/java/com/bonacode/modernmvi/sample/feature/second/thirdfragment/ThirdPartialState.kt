package com.bonacode.modernmvi.sample.feature.second.thirdfragment

import com.bonacode.modernmvi.core.PartialState

sealed class ThirdPartialState : PartialState<ThirdViewState, ThirdViewEffect> {
    object NavigateForward : ThirdPartialState() {
        override fun mapToViewEffect(): ThirdViewEffect {
            return ThirdViewEffect.NavigateForward
        }
    }
}
