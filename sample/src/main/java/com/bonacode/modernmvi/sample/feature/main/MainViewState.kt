package com.bonacode.modernmvi.sample.feature.main

import com.bonacode.modernmvi.core.ViewState

data class MainViewState(
    val counterValue: Int = 0
) : ViewState
