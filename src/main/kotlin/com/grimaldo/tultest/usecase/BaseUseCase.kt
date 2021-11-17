package com.grimaldo.tultest.usecase

abstract class BaseUseCase<P, R> {

    abstract operator fun invoke(param: P): R
}