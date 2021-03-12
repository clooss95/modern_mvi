package com.bonacode.modernmvi.sample.feature.main

import com.bonacode.modernmvi.core.ViewEffect

sealed class MainViewEffect : ViewEffect {
    object NavigateToSecondScreen : MainViewEffect()
}
