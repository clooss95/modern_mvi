package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import com.bonacode.modernmvi.core.ViewEffect

sealed class DogCreateViewEffect : ViewEffect {
    object NavigateBack : DogCreateViewEffect()
    object HideKeyboard : DogCreateViewEffect()
}
