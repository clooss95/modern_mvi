package com.example.modernmvi.feature.second.fragmentsecond

import com.example.modernmvi.base.ViewEffect

sealed class SecondViewEffect : ViewEffect {
    object NavigateForward : SecondViewEffect()
}
