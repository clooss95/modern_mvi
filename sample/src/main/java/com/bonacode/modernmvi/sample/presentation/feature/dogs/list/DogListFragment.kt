package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.FragmentDogListBinding
import com.bonacode.modernmvi.sample.presentation.common.setVisibility
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface DogListView : View<DogListViewState, DogListViewEffect, DogListIntent>

@AndroidEntryPoint
class DogListFragment :
    MviFragment<DogListViewState, DogListViewEffect, DogListView, DogListPresenter, FragmentDogListBinding>(
        R.layout.fragment_dog_list
    ), DogListView {

    override val binding: FragmentDogListBinding by viewBinding(FragmentDogListBinding::bind)
    override val presenter: DogListPresenter by viewModels()
    override fun getMviView(): DogListView = this

    override fun handleViewEffect(event: DogListViewEffect) {
        when (event) {
            is DogListViewEffect.NavigateToDogDetails -> navigateForward()
        }
    }

    override fun render(viewState: DogListViewState) {
        with(viewState) {
            (binding.dogList.adapter as? DogListAdapter)?.submitList(dogList)
            binding.progressBar.setVisibility(showProgressBar)
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dogList.adapter = DogListAdapter()
    }

    override fun emitIntents(): Observable<DogListIntent> =
        Observable.just(DogListIntent.RefreshDogList)

    private fun navigateForward() {
        findNavController().navigate(R.id.action_dog_list_fragment_to_dog_details_fragment)
    }
}