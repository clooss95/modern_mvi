package com.bonacode.modernmvi.sample.presentation.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object ViewAdapters {
    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:visibilitySoft")
    @JvmStatic
    fun setVisibilitySoft(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }
}