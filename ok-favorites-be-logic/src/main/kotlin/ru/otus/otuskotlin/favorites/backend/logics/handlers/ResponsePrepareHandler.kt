package ru.otus.otuskotlin.favorites.backend.logics.handlers

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.mp.cor.cor

val responsePrepareHandler = cor<FavoritesItemContext> {
    handler {
        isApplicable { status in arrayOf(FavoritesItemContextStatus.RUNNING, FavoritesItemContextStatus.FINISHING) }
        exec {
            status = FavoritesItemContextStatus.SUCCESS
        }
    }
    handler {
        isApplicable { status != FavoritesItemContextStatus.SUCCESS }
        exec { status = FavoritesItemContextStatus.ERROR }
    }
}
