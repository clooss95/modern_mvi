package com.bonacode.modernmvi.sample.presentation.feature.counter

import com.bonacode.modernmvi.core.Intent

sealed class CounterIntent : Intent {
    object Increase : CounterIntent()
    object Decrease : CounterIntent()
    object NavigateToSecondScreen : CounterIntent()
}
