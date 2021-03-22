package com.bonacode.modernmvi.sample.presentation.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewAdapters {
    @BindingAdapter("app:imageUrlCenterCrop")
    @JvmStatic
    fun bindImageUrl(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .into(view)
    }
}