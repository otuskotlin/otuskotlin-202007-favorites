import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.withCharset
import io.ktor.server.testing.*
import io.ktor.util.KtorExperimentalAPI
import kotlinx.serialization.json.Json
import org.junit.BeforeClass
import org.junit.Test
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResultStatuses
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class ApplicationStubTest {
    @Test
    fun testRoot() {
        with(engine) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }

    @Test
    fun getFavoritesItem() {
        with(engine) {
            handleRequest(HttpMethod.Post, "/favorites/get") {
                val body = KmpFavoritesItemGet(
                    userId = "user-id",
                    entityType = "photo",
                    entityId = "entity-id"
                )
                val bodyString = Json.encodeToString(KmpFavoritesItemGet.serializer(), body)
                setBody(bodyString)
                addHeader("Content-type", "application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                val res = Json.decodeFromString(KmpFavoritesItemResponse.serializer(), jsonString)
                assertEquals("user-id", res.data?.userId)
            }
        }
    }

    @Test
    fun indexFavoritesItem() {
        with(engine) {
            handleRequest(HttpMethod.Post, "/favorites/index") {
                val body = KmpFavoritesItemIndex(
                    limit = 10L,
                    offset = 5L,
                    filter = KmpFavoritesItemIndex.Filter(searchString = "created > today-15")
                )
                val bodyString = Json.encodeToString(KmpFavoritesItemIndex.serializer(), body)
                setBody(bodyString)
                addHeader("Content-type", "application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                val res = Json.decodeFromString(KmpFavoritesItemResponseIndex.serializer(), jsonString)
                assertTrue(res.data!!.size > 0)
            }
        }
    }

    @Test
    fun putFavoritesItem() {
        with(engine) {
            handleRequest(HttpMethod.Post, "/favorites/put") {
                val body = KmpFavoritesItemPut(
                    userId = "user-id",
                    entityType = "book",
                    entityId = "890",
                    description = "Kotlin for beginners",
                    uri = "http://ddhhd.org/jdjdj"
                )
                val bodyString = Json.encodeToString(KmpFavoritesItemPut.serializer(), body)
                setBody(bodyString)
                addHeader("Content-Type", "application/json")
            }
            .apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                val res = Json.decodeFromString(KmpFavoritesItemResponse.serializer(), jsonString)
                assertEquals("user-id", res.data?.userId)
            }
        }
    }

    @Test
    fun updateFavoritesItem() {
        with(engine) {
            handleRequest(HttpMethod.Post, "/favorites/update") {
                val body = KmpFavoritesItemUpdate(
                    userId = "user-id",
                    entityType = "book",
                    entityId = "890",
                    description = "Kotlin for beginners",
                    uri = "http://ddhhd.org/jdjdj"
                )
                val bodyString = Json.encodeToString(KmpFavoritesItemUpdate.serializer(), body)
                setBody(bodyString)
                addHeader("Content-Type", "application/json")
            }
                .apply {
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                    val jsonString = response.content ?: fail("Null response json")
                    val res = Json.decodeFromString(KmpFavoritesItemResponse.serializer(), jsonString)
                    assertEquals("user-id", res.data?.userId)
                }
        }
    }

    @Test
    fun removeFavoritesItem() {
        with(engine) {
            handleRequest(HttpMethod.Post, "/favorites/remove") {
                val body = KmpFavoritesItemRemove(
                    userId = "delete-id",
                    entityType = "photo",
                    entityId = "456"

                )
                val bodyString = Json.encodeToString(KmpFavoritesItemRemove.serializer(), body)
                setBody(bodyString)
                addHeader("Content-Type", "application/json")
            }
            .apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                val res = Json.decodeFromString(KmpFavoritesItemResponse.serializer(), jsonString)
                assertEquals("456", res.data?.entityId)
                assertEquals(KmpFavoritesResultStatuses.SUCCESS,res.status)
            }
        }
    }

    companion object {
        @OptIn(KtorExperimentalAPI::class)
        private val engine = TestApplicationEngine(createTestEnvironment {
            config = HoconApplicationConfig(ConfigFactory.load("application.conf"))
        })

        @BeforeClass
        @JvmStatic fun setup(){
            engine.start(wait = false)
        }
    }

}
