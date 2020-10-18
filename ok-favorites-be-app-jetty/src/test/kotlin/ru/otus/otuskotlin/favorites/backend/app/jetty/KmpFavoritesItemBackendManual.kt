package ru.otus.otuskotlin.favorites.backend.app.jetty

import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*
import java.util.*
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json

@Ignore
class KmpFavoritesItemBackendManual {
    @Test
    fun `call get controller`() {
        val kmpFavoritesItemGet = KmpFavoritesItemGet(
            userId = "user-id",
            entityType = "books",
            entityId = "678"
        )

        val (request, response, result) = "http://localhost:8080/favorites/get".httpPost()
            .jsonBody(Json.encodeToString(KmpFavoritesItemGet.serializer(), kmpFavoritesItemGet))
            .response()
        val itemResponse =
            Json.decodeFromString(KmpFavoritesItemResponse.serializer(), response.body().asString("application/json"))

        assertEquals(kmpFavoritesItemGet.userId, itemResponse.data?.userId)
        assertEquals(kmpFavoritesItemGet.entityType, itemResponse.data?.entityType)
        assertEquals(kmpFavoritesItemGet.entityId, itemResponse.data?.entityId)

    }

    @Test
    fun `call index controller`() {
        val kmpFavoritesItemIndex = KmpFavoritesItemIndex(
            limit = 10L,
            offset = 5L,
            filter = KmpFavoritesItemIndex.Filter(
                searchString = "created > today-15"
            )
        )

        val (request, response, result) = "http://localhost:8080/favorites/index".httpPost()
            .jsonBody(Json.encodeToString(KmpFavoritesItemIndex.serializer(), kmpFavoritesItemIndex))
            .response()
        val itemResponse =
            Json.decodeFromString(KmpFavoritesItemResponseIndex.serializer(), response.body().asString("application/json"))

        assertTrue(itemResponse.data!!.size>0)
    }

    @Test
    fun `call put controller`() {
        val kmpFavoritesItemPut = KmpFavoritesItemPut(
            userId = "user-id",
            entityType = "book",
            entityId = "678",
            description = "Kotlin for beginners",
            uri = "http://ddhhd.org/jdjdj"
        )

        val (request, response, result) = "http://localhost:8080/favorites/put".httpPost()
            .jsonBody(Json.encodeToString(KmpFavoritesItemPut.serializer(),kmpFavoritesItemPut))
            .response()
        val itemResponse =
            Json.decodeFromString(KmpFavoritesItemResponse.serializer(), response.body().asString("application/json"))

        assertEquals(kmpFavoritesItemPut.userId, itemResponse.data?.userId)
        assertEquals(kmpFavoritesItemPut.entityType, itemResponse.data?.entityType)
        assertEquals(kmpFavoritesItemPut.entityId, itemResponse.data?.entityId)
        assertEquals(kmpFavoritesItemPut.description, itemResponse.data?.description)
        assertEquals(kmpFavoritesItemPut.uri, itemResponse.data?.uri)
    }

    @Test
    fun `call update controller`() {
        val kmpFavoritesItemUpdate = KmpFavoritesItemUpdate(
            userId = "user-id",
            entityType = "book",
            entityId = "678",
            description = "Kotlin for beginners",
            uri = "http://ddhhd.org/jdjdj"
        )

        val (request, response, result) = "http://localhost:8080/favorites/update".httpPost()
            .jsonBody(Json.encodeToString(KmpFavoritesItemUpdate.serializer(),kmpFavoritesItemUpdate))
            .response()
        val itemResponse =
            Json.decodeFromString(KmpFavoritesItemResponse.serializer(), response.body().asString("application/json"))

        assertEquals(kmpFavoritesItemUpdate.userId, itemResponse.data?.userId)
        assertEquals(kmpFavoritesItemUpdate.entityType, itemResponse.data?.entityType)
        assertEquals(kmpFavoritesItemUpdate.entityId, itemResponse.data?.entityId)
        assertEquals(kmpFavoritesItemUpdate.description, itemResponse.data?.description)
        assertEquals(kmpFavoritesItemUpdate.uri, itemResponse.data?.uri)
    }

    @Test
    fun `call remove controller`() {
        val kmpFavoritesItemRemove = KmpFavoritesItemRemove(
            userId = "user-id",
            entityType = "book",
            entityId = "678"
        )

        val (request, response, result) = "http://localhost:8080/favorites/remove".httpPost()
            .jsonBody(Json.encodeToString(KmpFavoritesItemRemove.serializer(),kmpFavoritesItemRemove))
            .response()
        val itemResponse =
            Json.decodeFromString(KmpFavoritesItemResponse.serializer(), response.body().asString("application/json"))

        println(itemResponse)
        assertEquals(kmpFavoritesItemRemove.userId, itemResponse.data?.userId)
        assertEquals(kmpFavoritesItemRemove.entityType, itemResponse.data?.entityType)
        assertEquals(kmpFavoritesItemRemove.entityId, itemResponse.data?.entityId)

    }


}
