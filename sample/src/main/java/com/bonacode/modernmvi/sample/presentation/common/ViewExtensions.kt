package com.bonacode.modernmvi.sample.presentation.common

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun SwipeRefreshLayout.refreshes(): Observable<Unit> {
    val subject = PublishSubject.create<Unit>()
    setOnRefreshListener {
        subject.onNext(Unit)
    }
    return subject
}