package com.bonacode.modernmvi.core

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber

abstract class Presenter<VS : ViewState, V : View<VS, VE, IN>, PS : PartialState<VS, VE>, IN : Intent, VE : ViewEffect>(
    private val mainThread: Scheduler
) : ViewModel() {

    protected abstract val defaultViewState: VS

    private var view: V? = null

    private val intentSubject = PublishSubject.create<IN>()

    private val intentDisposable = CompositeDisposable()
    private val partialStateDisposable = CompositeDisposable()
    private val viewStateDisposable = CompositeDisposable()
    private val eventsDisposable = CompositeDisposable()

    private val viewStateSubject = BehaviorSubject.create<VS>()
    private val viewEffectSubject = PublishSubject.create<VE>()

    fun getViewState(): VS = viewStateSubject.value ?: defaultViewState

    internal fun setView(view: V) {
        this.view = view
    }

    internal fun clearView() {
        this.view = null
    }

    init {
        observePartialState(Observable.merge(mapIntents(), mapPresenterActions()).share())
    }

    internal fun bind() {
        observeViewState()
        observeViewEffects()
        observeViewIntents()
    }

    @CallSuper
    internal fun unbind() {
        intentDisposable.clear()
        viewStateDisposable.clear()
        eventsDisposable.clear()
    }

    @MainThread
    private fun observeViewState() {
        viewStateDisposable.add(
            viewStateSubject.distinctUntilChanged()
                .observeOn(mainThread)
                .subscribe(
                    { state ->
                        view?.render(state)
                    },
                    {
                        Timber.d("render view state error $it!")
                    },
                    {
                        Timber.d("render view state completed!")
                    }
                )
        )
    }

    @MainThread
    private fun observeViewEffects() {
        viewStateDisposable.add(
            viewEffectSubject
                .observeOn(mainThread)
                .subscribe(
                    { viewEffect ->
                        view?.handleViewEffect(viewEffect)
                    },
                    {
                        Timber.d("handle view effect error $it!")
                    },
                    {
                        Timber.d("handle view effect completed!")
                    }
                )
        )
    }

    @MainThread
    private fun observeViewIntents() {
        intentDisposable.add(
            (view?.emitIntents() ?: Observable.never())
                .subscribe(
                    { intent ->
                        intentSubject.onNext(intent)
                    },
                    {
                        Timber.d("handle intent error $it!")
                    },
                    {
                        Timber.d("handle intent completed!")
                    }
                )
        )
    }

    private fun observePartialState(partialStateStream: Observable<PS>) {
        partialStateDisposable.add(
            partialStateStream
                .observeOn(mainThread)
                .scan(getViewState(), this::reduce)
                .subscribeBy(
                    onNext = { viewState -> viewStateSubject.onNext(viewState) },
                    onError = { viewState -> viewStateSubject.onError(viewState) },
                    onComplete = { viewStateSubject.onComplete() }
                )
        )
        partialStateDisposable.add(
            partialStateStream
                .observeOn(mainThread)
                .flatMap { partialState ->
                    partialState.mapToViewEffect()
                        ?.let { viewEffect -> Observable.just(viewEffect) }
                        ?: Observable.never()
                }
                .subscribeBy(
                    onNext = { viewEffect -> viewEffectSubject.onNext(viewEffect) },
                    onError = { viewEffect -> viewEffectSubject.onError(viewEffect) },
                    onComplete = { viewEffectSubject.onComplete() }
                )
        )
    }

    override fun onCleared() {
        partialStateDisposable.dispose()
        super.onCleared()
    }

    private fun reduce(previousState: VS, partialState: PS): VS = partialState.reduce(previousState)

    private fun mapIntents(): Observable<PS> =
        intentSubject.flatMap { intentToPartialState(it) }

    private fun mapPresenterActions(): Observable<PS> =
        presenterAction().flatMap { intentToPartialState(it) }

    protected open fun presenterAction(): Observable<IN> = PublishSubject.create()

    abstract fun intentToPartialState(intent: IN): Observable<PS>
}
