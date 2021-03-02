package com.example.modernmvi.base

interface PartialState<VS : ViewState, VE : ViewEffect> {
    fun reduce(previousState: VS): VS = previousState
    fun mapToViewEffect(): VE? = null
}