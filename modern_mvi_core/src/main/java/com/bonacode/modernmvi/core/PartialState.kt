package com.bonacode.modernmvi.core

interface PartialState<VS : ViewState, VE : ViewEffect> {
    fun reduce(previousState: VS): VS = previousState
    fun mapToViewEffect(): VE? = null
}
