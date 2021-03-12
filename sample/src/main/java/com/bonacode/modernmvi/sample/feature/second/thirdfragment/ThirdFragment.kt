package com.bonacode.modernmvi.sample.feature.second.thirdfragment

import androidx.fragment.app.viewModels
import com.bonacode.modernmvi.R
import com.bonacode.modernmvi.core.MviFragment
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.FragmentFirstBinding
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface ThirdView : View<ThirdViewState, ThirdViewEffect, ThirdIntent>

@AndroidEntryPoint
class ThirdFragment : MviFragment<ThirdViewState, ThirdViewEffect, ThirdView, ThirdPresenter, FragmentFirstBinding>(
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