package com.example.modernmvi.feature.second.thirdfragment

import com.example.modernmvi.base.PartialState

sealed class ThirdPartialState : PartialState<ThirdViewState, ThirdViewEffect> {
    object NavigateForward : ThirdPartialState() {
        override fun mapToViewEffect(): ThirdViewEffect {
            return ThirdViewEffect.NavigateForward
        }
    }
}
