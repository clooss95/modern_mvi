package com.example.modernmvi.feature.second.fragmentsecond

import com.example.modernmvi.base.Intent

sealed class SecondIntent : Intent {
    object NavigateForward : SecondIntent()
}