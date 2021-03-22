package com.bonacode.modernmvi.sample.presentation.feature.dogs.details

import androidx.fragment.app.viewModels
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.FragmentDogDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface DogDetailsView : View<DogDetailsViewState, DogDetailsViewEffect, DogDetailsIntent>

@AndroidEntryPoint
class DogDetailsFragment : MviFragment<DogDetailsViewState, DogDetailsViewEffect, DogDetailsView, DogDetailsPresenter, FragmentDogDetailsBinding>(
        R.layout.fragment_dog_details
    ), DogDetailsView {

    override val binding: FragmentDogDetailsBinding by viewBinding(FragmentDogDetailsBinding::bind)
    override val presenter: DogDetailsPresenter by viewModels()
    override fun getMviView(): DogDetailsView = this

    override fun handleViewEffect(event: DogDetailsViewEffect) {

    }

    override fun emitIntents(): Observable<DogDetailsIntent> =
        Observable.never()

    companion object {
        const val ARG_DOG = "args_dog"
    }

}