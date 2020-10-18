package ru.otus.otuskotlin.favorites.backend.common

enum class FavoritesItemContextStatus {
    NONE,
    RUNNING,
    FINISHING,
    FAILING,
    SUCCESS,
    ERROR;

    val isError
        get() = this in arrayOf(FAILING, ERROR)
}
