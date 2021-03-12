package com.bonacode.modernmvi.sample.feature.second.fragmentfirst

import com.bonacode.modernmvi.core.Intent

sealed class FirstIntent : Intent {
    object NavigateForward : FirstIntent()
}