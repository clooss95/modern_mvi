package com.bonacode.modernmvi.sample.domain

import io.reactivex.rxjava3.core.Completable

interface CompletableUseCase<in Params> {
    fun buildCompletable(params: Params): Completable
}