package ru.otus.otuskotlin.favorites.backend.logics.handlers

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesGetStubCases
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.mp.cor.cor

val stubGet = cor<FavoritesItemContext> {
    isApplicable { stubGetCase != FavoritesGetStubCases.NONE }

    handler {
        isApplicable { stubGetCase == FavoritesGetStubCases.SUCCESS }
        exec {

            responseFavoritesItem = FavoritesItemModel(
                userId = requestFavoritesItem.userId,
                entityType = requestFavoritesItem.entityType,
                entityId = requestFavoritesItem.entityId,
                description = requestFavoritesItem.description,
                uri = requestFavoritesItem.uri
            )

            status = FavoritesItemContextStatus.FINISHING
        }
    }

}
