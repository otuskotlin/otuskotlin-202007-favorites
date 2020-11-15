package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesError
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResponse
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResultStatuses

@Serializable
data class KmpFavoritesItemIndex(
    var limit: Long? = null,
    var offset: Long? = null,
    var filter: Filter? = null,
    var debug: Debug? = null
) {
    @Serializable
    data class Filter(
        var searchString: String? = null,
        var entityType: String? = null
    )

    @Serializable
    data class Debug(
        val stub: StubCases? = null,
        //val db: KmpUserDbModes? = null
    )

    @Serializable
    enum class StubCases {
        NONE,
        SUCCESS
    }
}
