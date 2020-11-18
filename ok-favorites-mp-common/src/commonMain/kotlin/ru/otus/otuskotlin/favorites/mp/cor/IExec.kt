package ru.otus.otuskotlin.favorites.mp.validators.cor

interface IExec<T> {
    suspend fun exec(ctx: T)
}
