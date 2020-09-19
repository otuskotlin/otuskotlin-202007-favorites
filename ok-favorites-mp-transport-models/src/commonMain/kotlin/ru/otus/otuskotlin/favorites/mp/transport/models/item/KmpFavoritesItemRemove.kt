package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable

@Serializable
data class KmpFavoritesItemRemove(
    var userId: String? = null,
    var entityType: String? = null,
    var entityId: String? = null,
    var debug: Debug? = null
) {
    @Serializable
    class Debug {}
}
