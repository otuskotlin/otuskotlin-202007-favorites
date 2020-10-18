package ru.otus.otuskotlin.favorites.backend.app.jetty

import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*
import java.util.*
import kotlin.test.assertEquals

@ImplicitReflectionSerializer
@Ignore
class KmpFavoritesItemBackendManual {
    val json = Json(JsonConfiguration.Stable)

    @Test
    fun `call get controller`() {
        val kmpFavoritesItemGet = KmpFavoritesItemGet(
            userId = "user-id",
            entityType = "book",
            entityId = UUID.randomUUID().toString()
        )

        val (request, response, result) = "http://localhost:8080/favorites/get".httpPost()
            .jsonBody(json.stringify(KmpFavoritesItemGet.serializer(),kmpFavoritesItemGet))
            .response()
        val itemResponse = deserialize<KmpFavoritesItemResponse>(response)

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
            .jsonBody(json.stringify(KmpFavoritesItemIndex.serializer(),kmpFavoritesItemIndex))
            .response()
        val itemResponse = deserialize<KmpFavoritesItemResponseIndex>(response)

        assertTrue(itemResponse.data!!.size>0)
    }

    @Test
    fun `call put controller`() {
        val kmpFavoritesItemPut = KmpFavoritesItemPut(
            userId = "user-id",
            entityType = "book",
            entityId = UUID.randomUUID().toString(),
            description = "Kotlin for beginners",
            uri = "http://ddhhd.org/jdjdj"
        )

        val (request, response, result) = "http://localhost:8080/favorites/put".httpPost()
            .jsonBody(json.stringify(KmpFavoritesItemPut.serializer(),kmpFavoritesItemPut))
            .response()
        val itemResponse = deserialize<KmpFavoritesItemResponse>(response)

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
            entityId = UUID.randomUUID().toString(),
            description = "Kotlin for beginners",
            uri = "http://ddhhd.org/jdjdj"
        )

        val (request, response, result) = "http://localhost:8080/favorites/update".httpPost()
            .jsonBody(json.stringify(KmpFavoritesItemUpdate.serializer(),kmpFavoritesItemUpdate))
            .response()
        val itemResponse = deserialize<KmpFavoritesItemResponse>(response)

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
            entityId = UUID.randomUUID().toString()
        )

        val (request, response, result) = "http://localhost:8080/favorites/remove".httpPost()
            .jsonBody(json.stringify(KmpFavoritesItemRemove.serializer(),kmpFavoritesItemRemove))
            .response()
        val itemResponse = deserialize<KmpFavoritesItemResponse>(response)

        println(itemResponse)
        assertEquals(kmpFavoritesItemRemove.userId, itemResponse.data?.userId)
        assertEquals(kmpFavoritesItemRemove.entityType, itemResponse.data?.entityType)
        assertEquals(kmpFavoritesItemRemove.entityId, itemResponse.data?.entityId)

    }

    inline fun <reified T : Any> deserialize(r: Response): T {
        val je = json.parseJson(r.body().asString("application/json"))
        return json.fromJson(je)
    }
}
