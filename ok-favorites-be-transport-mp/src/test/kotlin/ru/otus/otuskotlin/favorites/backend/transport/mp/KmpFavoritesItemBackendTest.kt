package ru.otus.otuskotlin.favorites.backend.transport.mp

import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*
import kotlin.test.assertEquals

class KmpFavoritesItemBackendTest {
    val processor = Processor()
    val messageGet = KmpFavoritesItemGet( userId = "123",entityType = "photo",entityId = "345" )
    val messageIndex = KmpFavoritesItemIndex()
    val messagePut = KmpFavoritesItemPut(userId = "werwer")
    val messageUpdate = KmpFavoritesItemUpdate(userId = "123",entityType = "photo",entityId = "345", description = "greatest link")
    val messageRemove = KmpFavoritesItemRemove(userId = "123",entityType = "photo",entityId = "345")


    /**
     * Полагаем, что мы в методе контроллера
     */
    @Test
    fun `call get in controller`() {
        val context = FavoritesItemContext()
        val result = runBlocking {
            processor.get(context.setQuery(messageGet)).resultItem()
        }
        assertEquals("test-id", result.data?.userId)
    }

    @Test
    fun `call create in controller`() {
        val context = FavoritesItemContext()
        val result = runBlocking {
            processor.put(context.setQuery(messagePut)).resultItem()
        }
        assertEquals("test-id", result.data?.userId)
        assertEquals("http://localhost/123", result.data?.uri)
    }

    @Test
    fun `call update in controller`() {
        val context = FavoritesItemContext()
        val result = runBlocking {
            processor.update(context.setQuery(messageUpdate)).resultItem()
        }
        assertEquals("photo", result.data?.entityType)
    }

    @Test
    fun `call remove in controller`() {
        val context = FavoritesItemContext()
        val result = runBlocking {
            processor.remove(context.setQuery(messageRemove)).resultItem()
        }
        assertEquals("345", result.data?.entityId)
    }

    /**
     * Класс процессора моделирует модуль бизнес-логики
     */
    class Processor() {
        suspend fun get(context: FavoritesItemContext): FavoritesItemContext =
            context.apply { responseFavoritesItem = FavoritesItemModel(userId = "test-id" +
                "", description = "desc") }
        suspend fun index(context: FavoritesItemContext): FavoritesItemContext = context.apply {  }
        suspend fun put(context: FavoritesItemContext): FavoritesItemContext =
            context.apply { responseFavoritesItem = requestFavoritesItem.copy(userId = "test-id",uri = "http://localhost/123") }
        suspend fun update(context: FavoritesItemContext): FavoritesItemContext =
            context.apply { responseFavoritesItem = requestFavoritesItem.copy() }
        suspend fun remove(context: FavoritesItemContext): FavoritesItemContext =
            context.apply { responseFavoritesItem = FavoritesItemModel(userId = "123",entityType = "photo",entityId = "345") }
    }
}
