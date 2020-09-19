package ru.otus.otuskotlin.favorites.mp.transport.models

import kotlinx.serialization.Serializable

@Serializable
data class KmpFavoritesGet(
    val userId: String? = null,
    val debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug {}
}
