package com.example.modernmvi.feature.main

import com.example.modernmvi.base.ViewEffect

sealed class MainViewEffect : ViewEffect {
    object NavigateToSecondScreen : MainViewEffect()
}
