package com.example.modernmvi.feature.main

import android.content.Intent
import androidx.activity.viewModels
import com.example.modernmvi.base.MviActivity
import com.example.modernmvi.base.View
import com.example.modernmvi.base.viewBinding
import com.example.modernmvi.databinding.ActivityMainBinding
import com.example.modernmvi.feature.second.SecondScreenActivity
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable

interface MainView : View<MainViewState, MainViewEffect, MainIntent>

class MainActivity :
    MviActivity<MainViewState, MainViewEffect, MainView, MainPresenter, ActivityMainBinding>(),
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