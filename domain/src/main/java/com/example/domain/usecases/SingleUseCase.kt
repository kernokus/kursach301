package com.example.domain.usecases

interface SingleUseCase<R> {
    fun execute(): List<R>
}