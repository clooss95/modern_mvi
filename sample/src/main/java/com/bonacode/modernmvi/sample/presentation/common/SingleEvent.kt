package com.bonacode.modernmvi.sample.presentation.common

open class SingleEvent<out T> constructor(
    private val content: T
) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}
