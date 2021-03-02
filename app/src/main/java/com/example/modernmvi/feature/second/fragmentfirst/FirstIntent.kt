package com.example.modernmvi.feature.second.fragmentfirst

import com.example.modernmvi.base.Intent

sealed class FirstIntent : Intent {
    object NavigateForward : FirstIntent()
}