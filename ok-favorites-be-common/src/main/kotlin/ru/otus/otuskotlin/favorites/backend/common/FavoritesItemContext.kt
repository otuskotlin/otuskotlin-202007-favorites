package ru.otus.otuskotlin.favorites.backend.common

import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel

data class FavoritesItemContext (
    var requestFavoritesItemId: String = "",
    var requestFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,
    var responseFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE

)
