package com.bonacode.modernmvi.sample.presentation.common

import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputLayout
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

fun EditText.setTextDistinct(text: String?) {
    if (this.text.toString() != text) {
        this.setText(text)
    }
}
