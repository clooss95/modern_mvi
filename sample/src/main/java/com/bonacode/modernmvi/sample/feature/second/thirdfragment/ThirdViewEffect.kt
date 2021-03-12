package com.bonacode.modernmvi.sample.feature.second.thirdfragment

import com.bonacode.modernmvi.core.ViewEffect

sealed class ThirdViewEffect : ViewEffect {
    object NavigateForward : ThirdViewEffect()
}
