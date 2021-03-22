package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.FragmentDogCreateBinding
import com.bonacode.modernmvi.sample.presentation.common.observeTextChanges
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface DogCreateView : View<DogCreateViewState, DogCreateViewEffect, DogCreateIntent>

@AndroidEntryPoint
class DogCreateFragment :
    MviFragment<DogCreateViewState, DogCreateViewEffect, DogCreateView, DogCreatePresenter, FragmentDogCreateBinding>(
        R.layout.fragment_dog_create
    ), DogCreateView {
    override val binding: FragmentDogCreateBinding by viewBinding(FragmentDogCreateBinding::bind)
    override val presenter: DogCreatePresenter by viewModels()

    override fun getMviView(): DogCreateView = this

    override fun emitIntents(): Observable<DogCreateIntent> = Observable.merge(
        listOf(
            binding.nameEditText.observeTextChanges()
                .map { DogCreateIntent.NameChanged(it) },
            binding.breedEditText.observeTextChanges()
                .map { DogCreateIntent.BreedChanged(it) },
            binding.imageUrlEditText.observeTextChanges()
                .map { DogCreateIntent.ImageUrlChanged(it) },
            binding.saveButton.clicks()
                .map { DogCreateIntent.Save }
        )
    )

    override fun render(viewState: DogCreateViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    override fun handleViewEffect(event: DogCreateViewEffect) {
        if (event is DogCreateViewEffect.NavigateBack) {
            findNavController().popBackStack()
        }
    }
}