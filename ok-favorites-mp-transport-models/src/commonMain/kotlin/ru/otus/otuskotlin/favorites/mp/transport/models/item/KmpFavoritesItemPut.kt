package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable

@Serializable
data class KmpFavoritesItemPut(
    override var userId: String? = null,
    override var entityType: String? = null,
    override var entityId: String? = null,
    override var description: String? = null,
    override var uri: String? = null,
    var debug: Debug? = null
): KmpFavoritesItemSave(
    userId = userId,
    entityType = entityType,
    entityId = entityId,
    description = description,
    uri = uri
) {
    @Serializable
    data class Debug(
        val stub: StubCases? = null,
        //val db: KmpUserDbModes? = null
    ) {

    }

    @Serializable
    enum class StubCases {
        NONE,
        SUCCESS
    }}
