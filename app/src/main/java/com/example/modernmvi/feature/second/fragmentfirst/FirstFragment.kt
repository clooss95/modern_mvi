package com.example.modernmvi.feature.second.fragmentfirst

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.modernmvi.R
import com.example.modernmvi.base.MviFragment
import com.example.modernmvi.base.View
import com.example.modernmvi.base.viewBinding
import com.example.modernmvi.databinding.FragmentFirstBinding
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface FirstView : View<FirstViewState, FirstViewEffect, FirstIntent>

@AndroidEntryPoint
class FirstFragment :
    MviFragment<FirstViewState, FirstViewEffect, FirstView, FirstPresenter, FragmentFirstBinding>(
        R.layout.fragment_first
    ), FirstView {

    override val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)
    override val presenter: FirstPresenter by viewModels()
    override fun getMviView(): FirstView = this

    override fun handleViewEffect(event: FirstViewEffect) {
        when (event) {
            is FirstViewEffect.NavigateForward -> navigateForward()
        }
    }

    override fun emitIntents(): Observable<FirstIntent> =
        binding.navigateForwardButton.clicks().map { FirstIntent.NavigateForward }

    private fun navigateForward() {
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
    }
}