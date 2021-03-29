package com.bonacode.modernmvi.sample.domain

import io.reactivex.rxjava3.core.Observable

interface ObservableUseCase<in Params, T> {
    fun buildObservable(params: Params): Observable<T>
}
