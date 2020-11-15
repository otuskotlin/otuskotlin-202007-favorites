package ru.otus.otuskotlin.favorites.backend.logics

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesGetStubCases
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FavoritesItemGetChainTest {
    @Test
    fun crudGetTest() {
        val crud = FavoritesItemCrud()
        val context = FavoritesItemContext(
            requestUserId = expectedUserId,
            requestEntityId = expectedEntityId,
            requestEntityType = expectedEntityType,
            requestFavoritesItem = FavoritesItemModel (
                userId = expectedUserId,
                entityType = expectedEntityType,
                entityId = expectedEntityId
            ),
            stubGetCase = FavoritesGetStubCases.SUCCESS
        )
        runBlocking {
            crud.get(context)
        }

        assertEquals(FavoritesItemContextStatus.SUCCESS, context.status)
        assertEquals(expectedUserId, context.responseFavoritesItem.userId)
        assertEquals(expectedUserId, context.requestUserId)
    }

    companion object {
        val expectedUserId = "ru"
        val expectedEntityId = "ri"
        val expectedEntityType = "rt"
    }
}
