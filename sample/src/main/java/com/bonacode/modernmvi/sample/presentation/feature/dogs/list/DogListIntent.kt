package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.Intent
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

sealed class DogListIntent : Intent {
    object RefreshDogList : DogListIntent()
    object NavigateToCreate : DogListIntent()
    data class NavigateToDogDetails(val dog: Dog) : DogListIntent()
}
