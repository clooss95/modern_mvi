package com.bonacode.modernmvi.sample.presentation.feature.dogs.create

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.FragmentDogCreateBinding
import com.bonacode.modernmvi.sample.presentation.common.resError
import com.bonacode.modernmvi.sample.presentation.common.setTextDistinct
import com.bonacode.modernmvi.sample.presentation.common.setVisibility
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
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
            binding.nameEditText.textChanges()
                .map { DogCreateIntent.NameChanged(it.toString()) },
            binding.breedEditText.textChanges()
                .map { DogCreateIntent.BreedChanged(it.toString()) },
            binding.imageUrlEditText.textChanges()
                .map { DogCreateIntent.ImageUrlChanged(it.toString()) },
            binding.saveButton.clicks()
                .map { DogCreateIntent.Save }
        )
    )

    override fun render(viewState: DogCreateViewState) {
        with(viewState) {
            binding.progressBar.setVisibility(showProgressBar)
            binding.errorTextView.text = error?.message ?: ""
            binding.errorTextView.setVisibility(error != null)

            binding.nameEditText.setTextDistinct(name)
            binding.breedEditText.setTextDistinct(breed)
            binding.imageUrlEditText.setTextDistinct(imageUrl)

            binding.nameTextInputLayout.resError(nameError)
            binding.breedTextInputLayout.resError(breedError)

            binding.saveButton.isEnabled = formValid
        }
    }

    override fun handleViewEffect(event: DogCreateViewEffect) {
        if (event is DogCreateViewEffect.NavigateBack) {
            findNavController().popBackStack()
        }
    }
}