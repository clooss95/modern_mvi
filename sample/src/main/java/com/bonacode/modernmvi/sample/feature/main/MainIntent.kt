package com.bonacode.modernmvi.sample.feature.main

import com.bonacode.modernmvi.core.Intent

sealed class MainIntent : Intent {
    object Increase : MainIntent()
    object Decrease : MainIntent()
    object NavigateToSecondScreen : MainIntent()
}

