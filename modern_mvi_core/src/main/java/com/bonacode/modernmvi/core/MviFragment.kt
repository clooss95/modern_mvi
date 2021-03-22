package com.bonacode.modernmvi.core

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
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

    protected fun hideKeyboard() {
        activity?.currentFocus?.let { v ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

}
