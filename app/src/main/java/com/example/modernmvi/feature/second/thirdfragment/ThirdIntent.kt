package com.example.modernmvi.feature.second.thirdfragment

import com.example.modernmvi.base.Intent

sealed class ThirdIntent : Intent {
    object NavigateForward : ThirdIntent()
}