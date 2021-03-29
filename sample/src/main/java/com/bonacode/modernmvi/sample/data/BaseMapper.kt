package com.bonacode.modernmvi.sample.data

interface BaseMapper<R, T> {

    fun transform(input: R): T

    fun transform(input: List<R>): List<T> =
        input.map { transform(it) }

    fun reverse(input: T): R {
        throw NotImplementedError()
    }

    fun reverse(input: List<T>): List<R> =
        input.map { reverse(it) }
}
