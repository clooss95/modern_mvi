package com.bonacode.modernmvi.sample.feature.second.fragmentsecond

import com.bonacode.modernmvi.core.PartialState

sealed class SecondPartialState : PartialState<SecondViewState, SecondViewEffect> {
    object NavigateForward : SecondPartialState() {
        override fun mapToViewEffect(): SecondViewEffect {
            return SecondViewEffect.NavigateForward
        }
    }
}
