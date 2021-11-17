package com.grimaldo.tultest.app.shared

interface AbstractMapper<S, D> {
    fun map(source: S): D
}