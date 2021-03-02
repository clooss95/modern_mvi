package com.example.modernmvi.feature.second.fragmentsecond

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

interface SecondView : View<SecondViewState, SecondViewEffect, SecondIntent>

@AndroidEntryPoint
class SecondFragment :
    MviFragment<SecondViewState, SecondViewEffect, SecondView, SecondPresenter, FragmentFirstBinding>(
        R.layout.fragment_second
    ), SecondView {

    override val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)
    override val presenter: SecondPresenter by viewModels()
    override fun getMviView(): SecondView = this

    override fun handleViewEffect(event: SecondViewEffect) {
        when (event) {
            is SecondViewEffect.NavigateForward -> navigateForward()
        }
    }

    override fun emitIntents(): Observable<SecondIntent> =
        binding.navigateForwardButton.clicks().map { SecondIntent.NavigateForward }

    private fun navigateForward() {
        findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
    }
}