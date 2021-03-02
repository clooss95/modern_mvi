package com.example.modernmvi.base

import org.junit.Assert.assertEquals

abstract class ViewRobot<VS : ViewState, VE : ViewEffect, V : View<VS, VE, *>, out M : Presenter<VS, V, *, *, VE>>(
    private val presenter: M
) {
    val renderedStates: ArrayList<in VS> = arrayListOf()
    val emittedViewEffects: ArrayList<in VE> = arrayListOf()

    abstract val view: V

    fun assertViewStates(vararg expectedStates: VS) {
        assertEquals(expectedStates.toList(), renderedStates)
    }

    fun assertViewEffects(vararg expectedViewEffects: VE) {
        assertEquals(expectedViewEffects.toList(), emittedViewEffects)
    }

    fun test(action: () -> Unit) {
        startView()
        action()
        stopView()
    }

    fun startView() {
        presenter.setView(view)
        presenter.bind()
    }

    fun stopView() {
        presenter.unbind()
        presenter.clearView()
    }
}
