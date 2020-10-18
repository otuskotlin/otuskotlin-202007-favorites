package ru.otus.otuskotlin.favorites

import org.slf4j.LoggerFactory
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.errors.InternalServerError
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.transport.mp.resultIndex
import ru.otus.otuskotlin.favorites.backend.transport.mp.resultItem
import ru.otus.otuskotlin.favorites.backend.transport.mp.setQuery
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*
import java.lang.RuntimeException

class KmpFavoritesItemService {

    private val log = LoggerFactory.getLogger(this::class.java)!!

    private val favoritesItemModel = FavoritesItemModel (
        userId = "user_id",
        entityType = "books",
        entityId = "345",
        description = "kotlin fir beginners",
        uri = "https://social.org/books/123"
    )

    fun get(query: KmpFavoritesItemGet): KmpFavoritesItemResponse = FavoritesItemContext().run {
        try {
            setQuery(query)
            responseFavoritesItem = favoritesItemModel.copy(
                userId = query.userId ?: throw RuntimeException("No userId"),
                entityId = query.entityId ?: throw RuntimeException("No entityId"),
                entityType = query.entityType ?: throw RuntimeException("No entityType")
            )
        } catch (e: Throwable) {
            log.error("Get chain error", e)
            errors += InternalServerError.instance
        }
        resultItem()
    }

    fun index(query: KmpFavoritesItemIndex): KmpFavoritesItemResponseIndex = FavoritesItemContext().run {
        try {
            setQuery(query)
            responseFavoritesItem = favoritesItemModel.copy()
            status = FavoritesItemContextStatus.SUCCESS
        } catch (e: Throwable) {
            log.error("Index chain error", e)
            errors += InternalServerError.instance
        }
        resultIndex()
    }

    fun put(query: KmpFavoritesItemPut): KmpFavoritesItemResponse = FavoritesItemContext().run {
        try {
            setQuery(query)
            responseFavoritesItem = requestFavoritesItem.copy()
            status = FavoritesItemContextStatus.SUCCESS
        } catch (e: Throwable) {
            log.error("Create chain error", e)
            errors += InternalServerError.instance
        }
        resultItem()
    }

    fun update(query: KmpFavoritesItemUpdate): KmpFavoritesItemResponse = FavoritesItemContext().run {
        try {
            setQuery(query)
            responseFavoritesItem = requestFavoritesItem.copy()
            status = FavoritesItemContextStatus.SUCCESS
        } catch (e: Throwable) {
            log.error("Update chain error", e)
            errors += InternalServerError.instance
        }
        resultItem()
    }

    fun remove(query: KmpFavoritesItemRemove): KmpFavoritesItemResponse = FavoritesItemContext().run {
        try {
            setQuery(query)
            responseFavoritesItem = requestFavoritesItem.copy()
            status = FavoritesItemContextStatus.SUCCESS
        } catch (e: Throwable) {
            log.error("Delete chain error", e)
            errors += InternalServerError.instance
        }
        resultItem()
    }
}
