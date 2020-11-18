package ru.otus.otuskotlin.favorites.backend.logics

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesUpdateStubCases
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FavoritesItemUpdateChainTest {
    @Test
    fun crudUpdateTest() {
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
            stubUpdateCase = FavoritesUpdateStubCases.SUCCESS

        )
        runBlocking {
            crud.update(context)
        }

        assertEquals(FavoritesItemContextStatus.SUCCESS, context.status)
        assertEquals("u", context.responseFavoritesItem.userId)
        assertEquals("ru", context.requestUserId)
    }
}
