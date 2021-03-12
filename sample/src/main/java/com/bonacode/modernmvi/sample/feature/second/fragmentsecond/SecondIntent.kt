package com.bonacode.modernmvi.sample.feature.second.fragmentsecond

import com.bonacode.modernmvi.core.Intent

sealed class SecondIntent : Intent {
    object NavigateForward : SecondIntent()
}