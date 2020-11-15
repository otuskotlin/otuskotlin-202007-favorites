package ru.otus.otuskotlin.favorites.backend.logics.handlers

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesRemoveStubCases
import ru.otus.otuskotlin.favorites.mp.cor.cor

val stubRemove = cor<FavoritesItemContext> {
    isApplicable { stubRemoveCase != FavoritesRemoveStubCases.NONE }

    handler {
        isApplicable { stubRemoveCase == FavoritesRemoveStubCases.SUCCESS }
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
