package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesError
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResponse
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResultStatuses

@Serializable
data class KmpFavoritesItemResponseIndex(
    val data: List<KmpFavoritesItem>? = null,
    val limit: Long? = null,
    val offset: Long? = null,
    override val status: KmpFavoritesResultStatuses? = null,
    override val errors: List<KmpFavoritesError>? = null
): KmpFavoritesResponse(
    status = status,
    errors = errors
)
