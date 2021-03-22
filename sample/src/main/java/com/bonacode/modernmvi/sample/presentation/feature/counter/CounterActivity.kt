package com.bonacode.modernmvi.sample.presentation.feature.counter

import android.content.Intent
import androidx.activity.viewModels
import com.bonacode.modernmvi.core.MviActivity
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.ActivityCounterBinding
import com.bonacode.modernmvi.sample.presentation.feature.dogs.DogsActivity
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface CounterView : View<CounterViewState, CounterViewEffect, CounterIntent>

@AndroidEntryPoint
class CounterActivity : MviActivity<CounterViewState, CounterViewEffect, CounterView, CounterPresenter, ActivityCounterBinding>(),
    CounterView {

    override val binding: ActivityCounterBinding by viewBinding(ActivityCounterBinding::inflate)
    override val presenter: CounterPresenter by viewModels()
    override fun getMviView(): CounterView = this

    override fun render(viewState: CounterViewState) {
        binding.counter.text = viewState.counterValue.toString()
    }

    override fun handleViewEffect(event: CounterViewEffect) {
        when (event) {
            is CounterViewEffect.NavigateToSecondScreen -> navigateToSecondScreen()
        }
    }

    override fun emitIntents(): Observable<CounterIntent> = Observable.merge(
        binding.increaseButton.clicks().map { CounterIntent.Increase },
        binding.decreaseButton.clicks().map { CounterIntent.Decrease },
        binding.navigateForwardButton.clicks().map { CounterIntent.NavigateToSecondScreen }
    )

    private fun navigateToSecondScreen() {
        startActivity(
            Intent(
                this,
                DogsActivity::class.java
            )
        )
    }
}