package ru.otus.otuskotlin.favorites.mp.transport.models

import kotlinx.serialization.Serializable

@Serializable
enum class KmpFavoritesResultStatuses {
    SUCCESS,
    ERROR,
    WARNING
}
