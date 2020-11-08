package ru.otus.otuskotlin.favorites.backend.logics

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesPutStubCases
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FavoritesItemPutChainTest {
    @Test
    fun crudPutTest() {
        val crud = FavoritesItemCrud()
        val context = FavoritesItemContext(
            requestUserId = "ru",
            requestFavoritesItem = FavoritesItemModel(
                userId = "u",
                entityType = "t",
                entityId = "i",
                description = "d",
                uri = "u"
            ),
            stubPutCase = FavoritesPutStubCases.SUCCESS

        )
        runBlocking {
            crud.put(context)
        }

        assertEquals(FavoritesItemContextStatus.SUCCESS, context.status)
        assertEquals("u", context.responseFavoritesItem.userId)
        assertEquals("ru", context.requestUserId)
    }
}
