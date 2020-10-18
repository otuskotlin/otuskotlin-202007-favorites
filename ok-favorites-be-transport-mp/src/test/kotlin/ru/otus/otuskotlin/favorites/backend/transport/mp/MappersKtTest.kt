package ru.otus.otuskotlin.favorites.backend.transport.mp

import org.junit.Test
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.mp.transport.models.item.KmpFavoritesItemUpdate
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MappersKtTest {

    @Test
    fun userToModelTest() {
        val kmpFavoritesItemUpdate = KmpFavoritesItemUpdate(
            userId = "user-id",
            entityType = "friend",
            entityId = "entity-id",
            description = "odnoklassniki",
            uri = "https://social/user567"
        )

        val model = kmpFavoritesItemUpdate.model()
        assertEquals("user-id", model.userId)
        assertEquals("friend", model.entityType)
        assertEquals("entity-id", model.entityId)
        assertEquals("odnoklassniki", model.description)
        assertEquals("https://social/user567", model.uri)
    }


}
