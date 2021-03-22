package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.FragmentDogDetailsBinding
import com.bonacode.modernmvi.sample.presentation.common.setVisibility
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.lang.IllegalStateException

interface DogDetailsView : View<DogDetailsViewState, DogDetailsViewEffect, DogDetailsIntent>

@AndroidEntryPoint
class DogDetailsFragment :
    MviFragment<DogDetailsViewState, DogDetailsViewEffect, DogDetailsView, DogDetailsPresenter, FragmentDogDetailsBinding>(
        R.layout.fragment_dog_details
    ), DogDetailsView {

    private val dogIdSubject = BehaviorSubject.create<Long>()

    override val binding: FragmentDogDetailsBinding by viewBinding(FragmentDogDetailsBinding::bind)
    override val presenter: DogDetailsPresenter by viewModels()
    override fun getMviView(): DogDetailsView = this

    override fun render(viewState: DogDetailsViewState) {
        with(viewState) {
            binding.name.text = dog?.name ?: ""
            binding.breed.text = dog?.breed ?: ""
            dog?.imageUrl?.let { imageUrl ->
                Glide.with(binding.image)
                    .load(imageUrl)
                    .centerCrop()
                    .into(binding.image)
            }
            binding.container.setVisibility(dog != null)

            binding.progressBar.setVisibility(showProgressBar)

            binding.errorTextView.text = error?.message ?: ""
            binding.errorTextView.setVisibility(error != null)
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogIdSubject.onNext(
            arguments?.getLong(ARG_DOG_ID)
                ?: throw IllegalStateException("DogDetailsFragment can not be started without ARG_DOG_ID")
        )
    }

    override fun emitIntents(): Observable<DogDetailsIntent> =
        Observable.merge(
            listOf(
                dogIdSubject.map<DogDetailsIntent> { DogDetailsIntent.LoadDogDetails(it) }
            )
        )

    companion object {
        const val ARG_DOG_ID = "args_dog_id"
    }

}