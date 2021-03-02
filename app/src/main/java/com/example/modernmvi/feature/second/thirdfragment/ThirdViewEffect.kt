package com.example.modernmvi.feature.second.thirdfragment

import com.example.modernmvi.base.ViewEffect

sealed class ThirdViewEffect : ViewEffect {
    object NavigateForward : ThirdViewEffect()
}
