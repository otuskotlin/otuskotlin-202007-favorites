package ru.otus.otuskotlin.favorites.backend.common

import ru.otus.otuskotlin.favorites.backend.common.model.*

data class FavoritesItemContext(
    var requestUserId: String = "",
    var requestEntityType: String = "",
    var requestEntityId: String = "",

    var requestFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,

    var responseFavoritesItem: FavoritesItemModel = FavoritesItemModel.NONE,
    var responseFavorites: MutableList<FavoritesItemModel> = mutableListOf(),
    val errors: MutableList<FavoritesError> = mutableListOf(),

    var status: FavoritesItemContextStatus = FavoritesItemContextStatus.NONE,

    var workMode: WorkModes = WorkModes.DEFAULT,
    var stubPutCase: FavoritesPutStubCases = FavoritesPutStubCases.NONE,


//    var stubGetCase: UserGetStubCases = UserGetStubCases.NONE,
//    var stubIndexCase: UserIndexStubCases = UserIndexStubCases.NONE,
//    var stubUpdateCase: UserUpdateStubCases = UserUpdateStubCases.NONE,
//    var stubDeleteCase: UserDeleteStubCases = UserDeleteStubCases.NONE,

)
