package com.example.modernmvi.feature.main

import com.example.modernmvi.base.Intent

sealed class MainIntent : Intent{
    object Increase : MainIntent()
    object Decrease : MainIntent()
    object NavigateToSecondScreen : MainIntent()
}

