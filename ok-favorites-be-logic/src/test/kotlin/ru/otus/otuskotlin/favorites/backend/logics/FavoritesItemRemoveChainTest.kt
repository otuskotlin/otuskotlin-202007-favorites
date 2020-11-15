package ru.otus.otuskotlin.favorites.backend.logics

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesRemoveStubCases
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FavoritesItemRemoveChainTest {
    @Test
    fun crudRemoveTest() {
        val crud = FavoritesItemCrud()
        val context = FavoritesItemContext(
            requestUserId = expectedUserId,
            requestEntityId = expectedEntityId,
            requestEntityType = expectedType,
            requestFavoritesItem = FavoritesItemModel(
                userId = expectedUserId,
                entityType = expectedType,
                entityId = expectedEntityId
            ),
            stubRemoveCase = FavoritesRemoveStubCases.SUCCESS
        )

        runBlocking {
            crud.remove(context)
        }

        assertEquals(FavoritesItemContextStatus.SUCCESS, context.status)
        assertEquals(expectedUserId, context.responseFavoritesItem.userId)
        assertEquals(expectedUserId, context.requestUserId)
    }

    companion object {
        val expectedUserId = "ru"
        val expectedEntityId = "ri"
        val expectedType = "rt"
    }
}
