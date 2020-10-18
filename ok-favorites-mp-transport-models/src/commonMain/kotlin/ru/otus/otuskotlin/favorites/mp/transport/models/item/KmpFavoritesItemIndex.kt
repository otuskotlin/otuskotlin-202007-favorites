package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesError
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResponse
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResultStatuses

@Serializable
data class KmpFavoritesItemIndex(
    var limit: Long? = null,
    var offset: Long? = null,
    var filter: Filter? = null
) {
    @Serializable
    data class Filter(
        var searchString: String? = null
    )
}
