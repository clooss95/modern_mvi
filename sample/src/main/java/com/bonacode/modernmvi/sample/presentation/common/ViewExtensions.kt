package com.bonacode.modernmvi.sample.presentation.common

import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
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

fun TextInputLayout.resError(@StringRes res: Int?) {
    if (res == null) {
        this.error = null
    } else {
        this.error = context.getString(res)
    }
}

fun EditText.observeTextChanges(): Observable<String> =
    textChanges()
        .skipInitialValue()
        .map { it.toString() }
        .distinctUntilChanged()

infix fun <T> View.clicksTo(intent: T): Observable<T> = clicks().map { intent }
infix fun <T> SwipeRefreshLayout.refreshesTo(intent: T): Observable<T> = refreshes().map { intent }
infix fun <T> EditText.textChangesTo(mapper: (String) -> T): Observable<T> =
    observeTextChanges().map { mapper(it) }
