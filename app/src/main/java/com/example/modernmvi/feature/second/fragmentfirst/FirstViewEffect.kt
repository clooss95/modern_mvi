package com.example.modernmvi.feature.second.fragmentfirst

import com.example.modernmvi.base.ViewEffect

sealed class FirstViewEffect : ViewEffect {
    object NavigateForward : FirstViewEffect()
}
