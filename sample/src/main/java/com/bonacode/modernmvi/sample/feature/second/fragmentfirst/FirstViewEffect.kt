package com.bonacode.modernmvi.sample.feature.second.fragmentfirst

import com.bonacode.modernmvi.core.ViewEffect

sealed class FirstViewEffect : ViewEffect {
    object NavigateForward : FirstViewEffect()
}
