package com.bonacode.modernmvi.sample.feature.second.thirdfragment

import com.bonacode.modernmvi.core.Intent

sealed class ThirdIntent : Intent {
    object NavigateForward : ThirdIntent()
}