package com.bonacode.modernmvi.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class MviActivity<VS : ViewState, VE: ViewEffect, V : View<VS, VE, *>, P : Presenter<VS, V, *, *, VE>, VB: ViewBinding> : AppCompatActivity() {

    abstract val binding: VB

    abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.setView(getMviView())
    }

    override fun onDestroy() {
        presenter.clearView()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        presenter.bind()
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }

    protected abstract fun getMviView(): V

}
