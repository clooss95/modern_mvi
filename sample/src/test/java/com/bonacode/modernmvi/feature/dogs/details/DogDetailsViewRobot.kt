package com.bonacode.modernmvi.feature.dogs.details

import com.bonacode.modernmvi.core.ViewRobot
import com.bonacode.modernmvi.sample.presentation.feature.dogs.details.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class DogDetailsViewRobot(
    presenter: DogDetailsPresenter
) : ViewRobot<DogDetailsViewState, DogDetailsViewEffect, DogDetailsView, DogDetailsPresenter>(
    presenter
) {

    private val loadDogDetailsSubject = PublishSubject.create<DogDetailsIntent.LoadDogDetails>()

    override val view: DogDetailsView
        get() = object : DogDetailsView {
            override fun render(viewState: DogDetailsViewState) {
                renderedStates.add(viewState)
            }

            override fun handleViewEffect(event: DogDetailsViewEffect) {
                emittedViewEffects.add(event)
            }

            override fun emitIntents(): Observable<DogDetailsIntent> = Observable.merge(
                listOf(
                    loadDogDetailsSubject
                )
            )
        }

    fun loadDogDetails(id: Long) {
        loadDogDetailsSubject.onNext(DogDetailsIntent.LoadDogDetails(id))
    }
}