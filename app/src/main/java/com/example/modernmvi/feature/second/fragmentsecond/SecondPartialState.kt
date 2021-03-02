package com.example.modernmvi.feature.second.fragmentsecond

import com.example.modernmvi.base.PartialState

sealed class SecondPartialState : PartialState<SecondViewState, SecondViewEffect> {
    object NavigateForward : SecondPartialState() {
        override fun mapToViewEffect(): SecondViewEffect {
            return SecondViewEffect.NavigateForward
        }
    }
}
