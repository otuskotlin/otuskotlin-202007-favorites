package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class KmpFavoritesItemSave(
    @Transient open var userId: String? = null,
    @Transient open var entityType: String? = null,
    @Transient open var entityId: String? = null,
    @Transient open var description: String? = null,
    @Transient open var uri: String? = null
)
