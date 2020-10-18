package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable

@Serializable
data class KmpFavoritesItemGet(
    var userId: String? = null,
    var entityType: String? = null,
    var entityId: String? = null,
    val debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug {}
}
