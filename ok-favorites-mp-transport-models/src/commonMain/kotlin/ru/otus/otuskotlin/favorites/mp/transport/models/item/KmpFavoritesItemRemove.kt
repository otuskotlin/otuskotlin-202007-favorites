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
    data class Debug(
        val stub: StubCases? = null,
        //val db: KmpUserDbModes? = null
    ) {

    }

    @Serializable
    enum class StubCases {
        NONE,
        SUCCESS
    }
}
