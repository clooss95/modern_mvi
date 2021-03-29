package com.bonacode.modernmvi.presentation.feature.dogs.create

import com.bonacode.modernmvi.core.ViewRobot
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateIntent
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreatePresenter
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateView
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateViewEffect
import com.bonacode.modernmvi.sample.presentation.feature.dogs.create.DogCreateViewState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class DogCreateViewRobot(
    presenter: DogCreatePresenter
) : ViewRobot<DogCreateViewState, DogCreateViewEffect, DogCreateView, DogCreatePresenter>(presenter) {

    private val nameChangedSubject = PublishSubject.create<DogCreateIntent.NameChanged>()
    private val breedChangedSubject = PublishSubject.create<DogCreateIntent.BreedChanged>()
    private val imageUrlChangedSubject = PublishSubject.create<DogCreateIntent.ImageUrlChanged>()
    private val saveSubject = PublishSubject.create<DogCreateIntent.Save>()

    override val view: DogCreateView
        get() = object : DogCreateView {
            override fun render(viewState: DogCreateViewState) {
                renderedStates.add(viewState)
            }

            override fun handleViewEffect(event: DogCreateViewEffect) {
                emittedViewEffects.add(event)
            }

            override fun emitIntents(): Observable<DogCreateIntent> = Observable.merge(
                listOf(
                    nameChangedSubject,
                    breedChangedSubject,
                    imageUrlChangedSubject,
                    saveSubject
                )
            )
        }

    fun nameChanged(name: String) {
        nameChangedSubject.onNext(DogCreateIntent.NameChanged(name))
    }

    fun breedChanged(breed: String) {
        breedChangedSubject.onNext(DogCreateIntent.BreedChanged(breed))
    }

    fun imageUlrChanged(url: String) {
        imageUrlChangedSubject.onNext(DogCreateIntent.ImageUrlChanged(url))
    }

    fun saveClicked() {
        saveSubject.onNext(DogCreateIntent.Save)
    }
}
