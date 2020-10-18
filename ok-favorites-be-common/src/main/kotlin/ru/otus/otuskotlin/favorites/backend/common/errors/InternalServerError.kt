package ru.otus.otuskotlin.favorites.backend.common.errors

import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesError


data class InternalServerError(
    override val code: String = "internal-error",
    override val group: FavoritesError.Groups = FavoritesError.Groups.SERVER,
    override val field: String = "",
    override val level: FavoritesError.Levels = FavoritesError.Levels.ERROR,
    override val message: String = "Internal server error. If it continues to rise, please, apply to the Administrator"
) : FavoritesError {
    companion object {
        val instance = InternalServerError()
    }
}
