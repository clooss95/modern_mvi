package com.bonacode.modernmvi.sample.presentation.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewAdapters {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun errorText(view: TextView, error: Throwable?) {
        view.text = error?.message
    }
}