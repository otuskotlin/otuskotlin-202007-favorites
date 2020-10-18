package ru.otus.otuskotlin.favorites.mp.transport.models

import kotlinx.serialization.Serializable

@Serializable
data class KmpFavoritesError(
    val code: String? = null,
    val group: String? = null,
    val field: String? = null,
    val level: Level? = null,
    val message: String? = null
) {
    @Serializable
    enum class Level {
        SUCCESS,
        ERROR
    }
}

