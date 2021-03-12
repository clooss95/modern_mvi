package com.bonacode.modernmvi.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class MviFragment<VS : ViewState, VE : ViewEffect, V : View<VS, VE, *>, P : Presenter<VS, V, *, *, VE>, VB : ViewBinding>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    abstract val binding: VB

    abstract val presenter: P

    protected abstract fun getMviView(): V

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(getMviView())
    }

    override fun onDestroyView() {
        presenter.clearView()
        super.onDestroyView()
    }

    override fun onStart() {
        super.onStart()
        presenter.bind()
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }

}
