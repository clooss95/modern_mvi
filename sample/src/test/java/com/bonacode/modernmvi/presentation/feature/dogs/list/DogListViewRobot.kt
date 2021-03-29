package com.bonacode.modernmvi.presentation.feature.dogs.list

import com.bonacode.modernmvi.core.ViewRobot
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListIntent
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListPresenter
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListView
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListViewEffect
import com.bonacode.modernmvi.sample.presentation.feature.dogs.list.DogListViewState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class DogListViewRobot(
    presenter: DogListPresenter
) : ViewRobot<DogListViewState, DogListViewEffect, DogListView, DogListPresenter>(presenter) {

    private val refreshDogListSubject = PublishSubject.create<DogListIntent.RefreshDogList>()
    private val navigateToCreateSubject = PublishSubject.create<DogListIntent.NavigateToCreate>()
    private val navigateToDogDetailsSubject = PublishSubject.create<DogListIntent.NavigateToDogDetails>()

    override val view: DogListView
        get() = object : DogListView {
            override fun render(viewState: DogListViewState) {
                renderedStates.add(viewState)
            }

            override fun handleViewEffect(event: DogListViewEffect) {
                emittedViewEffects.add(event)
            }

            override fun emitIntents(): Observable<DogListIntent> = Observable.merge(
                listOf(
                    refreshDogListSubject,
                    navigateToCreateSubject,
                    navigateToDogDetailsSubject
                )
            )
        }

    fun refreshDogList() {
        refreshDogListSubject.onNext(DogListIntent.RefreshDogList)
    }

    fun navigateToCreate() {
        navigateToCreateSubject.onNext(DogListIntent.NavigateToCreate)
    }

    fun navigateToDogDetails(dog: Dog) {
        navigateToDogDetailsSubject.onNext(DogListIntent.NavigateToDogDetails(dog))
    }
}
