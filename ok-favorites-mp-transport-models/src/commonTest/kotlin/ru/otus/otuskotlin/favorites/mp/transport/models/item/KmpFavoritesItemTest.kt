package ru.otus.otuskotlin.favorites.mp.transport.models.item

import kotlin.test.Test
import kotlin.test.assertEquals

internal class KmpFavoritesItemTest {
    @Test
    fun createKmpFavoritesItem() {
        val kmpFavoritesItem = KmpFavoritesItem(
            userId = "123",
            entityType = "Photo",
            entityId = "456"
        )

        assertEquals("123", kmpFavoritesItem.userId)
    }
}
