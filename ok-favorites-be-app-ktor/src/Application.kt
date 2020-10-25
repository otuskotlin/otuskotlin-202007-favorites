package ru.otus.otuskotlin.favorites

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.content.defaultResource
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.path
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.serialization.json

import org.slf4j.event.Level
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val service = KmpFavoritesItemService()

    install(CallLogging) {
        level = Level.TRACE
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        json(
            contentType = ContentType.Application.Json
        )
    }

    routing {
        static("/") {
            defaultResource("static/index.html")
            resources("static")
        }

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        route("/favorites") {
            post("/get") {
                val query = call.receive<KmpFavoritesItemGet>()
                call.respond(service.get(query))
            }
            post("/index") {
                val query = call.receive<KmpFavoritesItemIndex>()
                call.respond(service.index(query));
            }
            post("/put") {
                val query = call.receive<KmpFavoritesItemPut>()
                call.respond(service.put(query))
            }
            post("/update") {
                val query =  call.receive<KmpFavoritesItemUpdate>()
                call.respond(service.update(query))
            }
            post("/remove") {
                val query = call.receive<KmpFavoritesItemRemove>()
                call.respond(service.remove(query))
            }

        }
    }
}

