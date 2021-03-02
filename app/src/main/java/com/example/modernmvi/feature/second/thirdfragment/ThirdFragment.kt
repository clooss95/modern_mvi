package com.example.modernmvi.feature.second.thirdfragment

import androidx.fragment.app.viewModels
import com.example.modernmvi.R
import com.example.modernmvi.base.MviFragment
import com.example.modernmvi.base.View
import com.example.modernmvi.base.viewBinding
import com.example.modernmvi.databinding.FragmentFirstBinding
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface ThirdView : View<ThirdViewState, ThirdViewEffect, ThirdIntent>

@AndroidEntryPoint
class ThirdFragment :
    MviFragment<ThirdViewState, ThirdViewEffect, ThirdView, ThirdPresenter, FragmentFirstBinding>(
        R.layout.fragment_third
    ), ThirdView {

    override val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)
    override val presenter: ThirdPresenter by viewModels()
    override fun getMviView(): ThirdView = this

    override fun handleViewEffect(event: ThirdViewEffect) {
        when (event) {
            is ThirdViewEffect.NavigateForward -> navigateForward()
        }
    }

    override fun emitIntents(): Observable<ThirdIntent> =
        binding.navigateForwardButton.clicks().map { ThirdIntent.NavigateForward }

    private fun navigateForward() {

    }
}