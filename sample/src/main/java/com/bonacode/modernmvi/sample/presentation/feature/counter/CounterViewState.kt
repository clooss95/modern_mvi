package com.bonacode.modernmvi.sample.presentation.feature.counter

import com.bonacode.modernmvi.core.ViewState

data class CounterViewState(
    val counterValue: Int = 0
) : ViewState {
    val counterValueText: String = "$counterValue"
}
