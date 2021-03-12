package com.bonacode.modernmvi

import com.bonacode.modernmvi.core.ViewRobot
import com.bonacode.modernmvi.sample.feature.main.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class MainPresenterViewRobot(
    presenter: MainPresenter
) : ViewRobot<MainViewState, MainViewEffect, MainView, MainPresenter>(presenter) {

    private val increaseSubject = PublishSubject.create<MainIntent.Increase>()
    private val decreaseSubject = PublishSubject.create<MainIntent.Decrease>()
    private val navigateToSecondScreenSubject =
        PublishSubject.create<MainIntent.NavigateToSecondScreen>()

    override val view: MainView = object : MainView {
        override fun render(viewState: MainViewState) {
            renderedStates.add(viewState)
        }

        override fun handleViewEffect(event: MainViewEffect) {
            emittedViewEffects.add(event)
        }

        override fun emitIntents(): Observable<MainIntent> = Observable.merge(
            increaseSubject,
            decreaseSubject,
            navigateToSecondScreenSubject
        )
    }

    fun increase() {
        increaseSubject.onNext(MainIntent.Increase)
    }

    fun decrease() {
        decreaseSubject.onNext(MainIntent.Decrease)
    }

    fun navigateToSecondScreen() {
        navigateToSecondScreenSubject.onNext(MainIntent.NavigateToSecondScreen)
    }

}