package ru.otus.otuskotlin.favorites.backend.common

import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesError
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel

data class FavoritesItemContext(
    var requestUserId: String = "",
    var requestEntityType: String = "",
    var requestEntityId: String = "",

    var requestFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,

    var responseFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,
    var responseFavorites: MutableList<FavoritesItemModel> = mutableListOf(),
    val errors: MutableList<FavoritesError> = mutableListOf(),
    var status: FavoritesItemContextStatus = FavoritesItemContextStatus.NONE

)
