package ru.otus.otuskotlin.favorites.backend.transport.mp

import org.slf4j.LoggerFactory
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.errors.InternalServerError
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.mp.transport.models.item.KmpFavoritesItem
import ru.otus.otuskotlin.favorites.mp.transport.models.item.KmpFavoritesItemGet
import ru.otus.otuskotlin.favorites.mp.transport.models.item.KmpFavoritesItemResponse
import java.lang.RuntimeException

class KmpFavoritesItemService {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    private val favoritesItemModel = FavoritesItemModel(
        userId = "123",
        entityType = "goods",
        entityId = "456",
        description = "Книга про многопоточность"
    )

    suspend fun get(query: KmpFavoritesItemGet): KmpFavoritesItemResponse = FavoritesItemContext().run {
        try {
            setQuery(query)
            responseFavoritesItem = favoritesItemModel.copy(userId = query.userId ?: throw RuntimeException("No userId"))
            status = FavoritesItemContextStatus.SUCCESS
        } catch (e: Throwable) {
            log.error("Get chain error", e)
            errors += InternalServerError.instance
        }
        resultItem()
    }
}
