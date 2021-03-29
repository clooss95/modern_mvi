package com.bonacode.modernmvi.core

import io.reactivex.rxjava3.core.Observable

interface View<in VS : ViewState, in E : ViewEffect, I : Intent> {

    fun render(viewState: VS) {}

    fun handleViewEffect(event: E) {}

    fun emitIntents(): Observable<I> = Observable.never()
}
