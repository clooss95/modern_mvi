package com.bonacode.modernmvi.presentation.feature.counter

import com.bonacode.modernmvi.core.ViewRobot
import com.bonacode.modernmvi.sample.presentation.feature.counter.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class CounterViewRobot(
    presenter: CounterPresenter
) : ViewRobot<CounterViewState, CounterViewEffect, CounterView, CounterPresenter>(presenter) {

    private val increaseSubject = PublishSubject.create<CounterIntent.Increase>()
    private val decreaseSubject = PublishSubject.create<CounterIntent.Decrease>()
    private val navigateToSecondScreenSubject =
        PublishSubject.create<CounterIntent.NavigateToSecondScreen>()

    override val view: CounterView = object : CounterView {
        override fun render(viewState: CounterViewState) {
            renderedStates.add(viewState)
        }

        override fun handleViewEffect(event: CounterViewEffect) {
            emittedViewEffects.add(event)
        }

        override fun emitIntents(): Observable<CounterIntent> = Observable.merge(
            increaseSubject,
            decreaseSubject,
            navigateToSecondScreenSubject
        )
    }

    fun increase() {
        increaseSubject.onNext(CounterIntent.Increase)
    }

    fun decrease() {
        decreaseSubject.onNext(CounterIntent.Decrease)
    }

    fun navigateToSecondScreen() {
        navigateToSecondScreenSubject.onNext(CounterIntent.NavigateToSecondScreen)
    }

}