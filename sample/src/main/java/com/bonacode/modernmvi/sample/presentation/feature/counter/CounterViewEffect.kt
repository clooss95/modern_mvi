package com.bonacode.modernmvi.sample.presentation.feature.counter

import com.bonacode.modernmvi.core.ViewEffect

sealed class CounterViewEffect : ViewEffect {
    object NavigateToSecondScreen : CounterViewEffect()
}
