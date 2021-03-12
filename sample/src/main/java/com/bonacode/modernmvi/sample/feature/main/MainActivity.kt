package com.bonacode.modernmvi.sample.feature.main

import android.content.Intent
import androidx.activity.viewModels
import com.bonacode.modernmvi.core.MviActivity
import com.bonacode.modernmvi.core.View
import com.bonacode.modernmvi.core.viewBinding
import com.bonacode.modernmvi.databinding.ActivityMainBinding
import com.bonacode.modernmvi.sample.feature.second.SecondScreenActivity
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

interface MainView : View<MainViewState, MainViewEffect, MainIntent>

@AndroidEntryPoint
class MainActivity : MviActivity<MainViewState, MainViewEffect, MainView, MainPresenter, ActivityMainBinding>(),
    MainView {

    override val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    override val presenter: MainPresenter by viewModels()
    override fun getMviView(): MainView = this

    override fun render(viewState: MainViewState) {
        binding.counter.text = viewState.counterValue.toString()
    }

    override fun handleViewEffect(event: MainViewEffect) {
        when (event) {
            is MainViewEffect.NavigateToSecondScreen -> navigateToSecondScreen()
        }
    }

    override fun emitIntents(): Observable<MainIntent> = Observable.merge(
        binding.increaseButton.clicks().map { MainIntent.Increase },
        binding.decreaseButton.clicks().map { MainIntent.Decrease },
        binding.navigateForwardButton.clicks().map { MainIntent.NavigateToSecondScreen }
    )

    private fun navigateToSecondScreen() {
        startActivity(
            Intent(
                this,
                SecondScreenActivity::class.java
            )
        )
    }
}