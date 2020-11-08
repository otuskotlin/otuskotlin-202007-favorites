package ru.otus.otuskotlin.favorites.backend.common.errors

import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesError

data class GeneralError(
        override val code: String = "",
        override val group: FavoritesError.Groups,
        override val field: String = "",
        override val level: FavoritesError.Levels = FavoritesError.Levels.ERROR,
        override val message: String = ""
) : FavoritesError {
    constructor(
            code: String,
            group: FavoritesError.Groups = FavoritesError.Groups.SERVER,
            e: Throwable
    ): this(
            code = "",
            group = FavoritesError.Groups.SERVER ,
            field = "",
            level = FavoritesError.Levels.ERROR,
            message = e.message ?: "Unknown exception"
    )
}
