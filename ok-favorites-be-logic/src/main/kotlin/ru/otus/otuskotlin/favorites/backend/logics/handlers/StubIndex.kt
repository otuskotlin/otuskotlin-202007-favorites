package ru.otus.otuskotlin.favorites.backend.logics.handlers

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesIndexStubCases
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.mp.cor.cor


val stubIndex = cor<FavoritesItemContext> {
    isApplicable { stubIndexCase != FavoritesIndexStubCases.NONE }
    handler {
        isApplicable { stubIndexCase == FavoritesIndexStubCases.SUCCESS }
        exec {
            responseFavorites = mutableListOf(
                FavoritesItemModel(
                    entityId = "123"
                ),
                FavoritesItemModel(
                    entityId = "456"
                )
            )
            status = FavoritesItemContextStatus.FINISHING
        }
    }

}
