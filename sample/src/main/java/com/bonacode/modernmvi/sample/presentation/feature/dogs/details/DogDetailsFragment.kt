package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.databinding.FragmentDogDetailsBinding
import com.bonacode.modernmvi.sample.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.lang.IllegalStateException

interface DogDetailsView : View<DogDetailsViewState, DogDetailsViewEffect, DogDetailsIntent>

@AndroidEntryPoint
class DogDetailsFragment :
    MviFragment<DogDetailsViewState, DogDetailsViewEffect, DogDetailsView, DogDetailsPresenter, FragmentDogDetailsBinding>(
        R.layout.fragment_dog_details
    ),
    DogDetailsView {

    private val dogIdSubject = BehaviorSubject.create<Long>()

    override val binding: FragmentDogDetailsBinding by viewBinding(FragmentDogDetailsBinding::bind)
    override val presenter: DogDetailsPresenter by viewModels()
    override fun getMviView(): DogDetailsView = this

    override fun render(viewState: DogDetailsViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogIdSubject.onNext(
            arguments?.getLong(ARG_DOG_ID)
                ?: throw IllegalStateException("DogDetailsFragment can not be started without ARG_DOG_ID")
        )
    }

    override fun emitIntents(): Observable<DogDetailsIntent> =
        dogIdSubject.map { DogDetailsIntent.LoadDogDetails(it) }

    companion object {
        const val ARG_DOG_ID = "args_dog_id"
    }
}
