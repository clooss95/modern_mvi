package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.databinding.FragmentDogCreateBinding
import com.bonacode.modernmvi.sample.presentation.common.clicksTo
import com.bonacode.modernmvi.sample.presentation.common.hideKeyboard
import com.bonacode.modernmvi.sample.presentation.common.textChangesTo
import com.bonacode.modernmvi.sample.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface DogCreateView : View<DogCreateViewState, DogCreateViewEffect, DogCreateIntent>

@AndroidEntryPoint
class DogCreateFragment :
    MviFragment<DogCreateViewState, DogCreateViewEffect, DogCreateView, DogCreatePresenter, FragmentDogCreateBinding>(
        R.layout.fragment_dog_create
    ),
    DogCreateView {
    override val binding: FragmentDogCreateBinding by viewBinding(FragmentDogCreateBinding::bind)
    override val presenter: DogCreatePresenter by viewModels()

    override fun getMviView(): DogCreateView = this

    override fun emitIntents(): Observable<DogCreateIntent> = Observable.merge(
        listOf(
            binding.nameEditText textChangesTo { DogCreateIntent.NameChanged(it) },
            binding.breedEditText textChangesTo { DogCreateIntent.BreedChanged(it) },
            binding.imageUrlEditText textChangesTo { DogCreateIntent.ImageUrlChanged(it) },
            binding.saveButton clicksTo DogCreateIntent.Save
        )
    )

    override fun render(viewState: DogCreateViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    override fun handleViewEffect(event: DogCreateViewEffect) {
        when (event) {
            is DogCreateViewEffect.NavigateBack -> findNavController().popBackStack()
            is DogCreateViewEffect.HideKeyboard -> hideKeyboard()
        }
    }
}
