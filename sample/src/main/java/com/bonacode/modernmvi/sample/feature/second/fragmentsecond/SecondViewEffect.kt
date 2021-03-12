package com.bonacode.modernmvi.sample.feature.second.fragmentsecond

import com.bonacode.modernmvi.core.ViewEffect

sealed class SecondViewEffect : ViewEffect {
    object NavigateForward : SecondViewEffect()
}
