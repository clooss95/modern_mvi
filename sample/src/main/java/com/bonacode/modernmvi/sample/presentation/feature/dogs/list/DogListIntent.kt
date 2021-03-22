package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.Intent

sealed class DogListIntent : Intent {
    object NavigateToDogDetails : DogListIntent()
}