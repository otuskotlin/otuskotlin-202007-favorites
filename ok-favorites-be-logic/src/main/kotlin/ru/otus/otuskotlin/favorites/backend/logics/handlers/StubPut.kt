package ru.otus.otuskotlin.favorites.backend.logics.handlers

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesPutStubCases
import ru.otus.otuskotlin.favorites.mp.cor.cor

val stubPut = cor<FavoritesItemContext> {
    isApplicable { stubPutCase != FavoritesPutStubCases.NONE }

    handler {
        isApplicable { stubPutCase == FavoritesPutStubCases.SUCCESS }
        exec {
            responseFavoritesItem = FavoritesItemModel(
                userId = requestUserId,
                entityType = requestEntityId,
                entityId = requestEntityId,
                description = requestFavoritesItem.description,
                uri = requestFavoritesItem.uri
            )

            status = FavoritesItemContextStatus.FINISHING
        }
    }

}
