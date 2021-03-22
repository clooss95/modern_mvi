package com.bonacode.modernmvi.sample.presentation.databinding

import androidx.databinding.BindingAdapter
import com.bonacode.modernmvi.sample.presentation.common.resError
import com.google.android.material.textfield.TextInputLayout

object TextInputLayoutAdapters {
    @BindingAdapter("app:resError")
    @JvmStatic
    fun resError(view: TextInputLayout, stringRes: Int?) {
        view.resError(stringRes)
    }
}