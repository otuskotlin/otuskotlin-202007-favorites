package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable

@Serializable
data class KmpFavoritesItem (
    var userId: String? = null,
    var entityType: String? = null,
    var entityId: String? = null,
    var description: String? = null,
    var uri: String? = null,
    var permissions: MutableSet<String>? = null
)
