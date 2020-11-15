package ru.otus.otuskotlin.favorites.backend.common

import ru.otus.otuskotlin.favorites.backend.common.model.*

data class FavoritesItemContext(
    var requestUserId: String = "",
    var requestEntityType: String = "",
    var requestEntityId: String = "",

    var requestFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,
    var requestFavoritesItemFilter: FavoritesItemIndexFilter  = FavoritesItemIndexFilter.NONE,
    var responseFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,
    var responseFavorites: MutableList<FavoritesItemModel> = mutableListOf(),
    val errors: MutableList<FavoritesError> = mutableListOf(),

    var status: FavoritesItemContextStatus = FavoritesItemContextStatus.NONE,

    var workMode: WorkModes = WorkModes.DEFAULT,
    var stubPutCase: FavoritesPutStubCases = FavoritesPutStubCases.NONE,
    var stubGetCase: FavoritesGetStubCases = FavoritesGetStubCases.NONE,
    var stubIndexCase: FavoritesIndexStubCases = FavoritesIndexStubCases.NONE,
    var stubUpdateCase: FavoritesUpdateStubCases = FavoritesUpdateStubCases.NONE,
    var stubRemoveCase: FavoritesRemoveStubCases = FavoritesRemoveStubCases.NONE
)
