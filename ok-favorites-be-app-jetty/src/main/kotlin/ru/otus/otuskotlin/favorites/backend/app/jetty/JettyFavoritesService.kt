package ru.otus.otuskotlin.favorites.backend.app.jetty

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.transport.mp.resultIndex
import ru.otus.otuskotlin.favorites.backend.transport.mp.resultItem
import ru.otus.otuskotlin.favorites.backend.transport.mp.setQuery
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*

class JettyFavoritesService {

    private val favoritesItemModel = FavoritesItemModel(
                userId = "555",
                entityType = "photo",
                entityId = "777",
                description = "Крым",
                uri = ""
            )

    fun get(query: KmpFavoritesItemGet): KmpFavoritesItemResponse = runBlocking {
        val context = FavoritesItemContext()
        context
            .setQuery(query)
            .apply {
                responseFavoritesItem.userId = query.userId ?: ""
                responseFavoritesItem.entityType = query.entityType ?: ""
                responseFavoritesItem.entityId = query.entityId ?: ""
                status = FavoritesItemContextStatus.SUCCESS
            }
            .resultItem()
    }

    fun index(query: KmpFavoritesItemIndex): KmpFavoritesItemResponseIndex = runBlocking {
        val context = FavoritesItemContext()
        context
            .setQuery(query)
            .apply {
                responseFavoritesItem = favoritesItemModel
                status = FavoritesItemContextStatus.SUCCESS
            }
            .resultIndex()
    }

    fun put(query: KmpFavoritesItemPut): KmpFavoritesItemResponse = runBlocking {
        val context = FavoritesItemContext()
        context
            .setQuery(query)
            .apply {
                responseFavoritesItem = requestFavoritesItem.copy()
            }
            .resultItem()

    }

    fun update(query: KmpFavoritesItemUpdate): KmpFavoritesItemResponse = runBlocking {
        val context = FavoritesItemContext()
        context
            .setQuery(query)
            .apply {
                responseFavoritesItem = requestFavoritesItem.copy()
                status = FavoritesItemContextStatus.SUCCESS
            }
            .resultItem()
    }

    fun remove(query: KmpFavoritesItemRemove): KmpFavoritesItemResponse = runBlocking {
        val context = FavoritesItemContext()
        val q = query
        print(q)
        context
            .setQuery(query)
            .apply {
                responseFavoritesItem = requestFavoritesItem.copy()
                status = FavoritesItemContextStatus.SUCCESS
            }
            .resultItem()
    }
}
