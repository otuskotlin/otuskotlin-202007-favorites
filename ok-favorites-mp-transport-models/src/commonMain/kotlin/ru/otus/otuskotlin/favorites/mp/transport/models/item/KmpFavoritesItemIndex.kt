package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable

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
