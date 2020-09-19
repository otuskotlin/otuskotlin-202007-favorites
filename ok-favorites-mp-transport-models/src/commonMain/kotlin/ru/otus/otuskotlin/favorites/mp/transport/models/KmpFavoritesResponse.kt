package ru.otus.otuskotlin.favorites.mp.transport.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
open class KmpFavoritesResponse(
    @Transient open val status: KmpFavoritesResultStatuses? = null,
    @Transient open val errors: List<KmpFavoritesError>? = null
)
