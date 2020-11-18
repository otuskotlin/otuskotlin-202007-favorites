package ru.otus.otuskotlin.favorites.backend.logics

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesIndexStubCases
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemIndexFilter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class FavoritesItemIndexChainTest {
    @Test
    fun crudIndexTest() {
        val crud = FavoritesItemCrud()
        val context = FavoritesItemContext(
            requestFavoritesItemFilter = FavoritesItemIndexFilter(
                searchString = "all",
                entityType = "photo"
            ),
            stubIndexCase = FavoritesIndexStubCases.SUCCESS
        )
        runBlocking {
            crud.index(context)
        }

        assertEquals(FavoritesItemContextStatus.SUCCESS, context.status)
        assertTrue(context.responseFavorites.size > 0)

    }
}
