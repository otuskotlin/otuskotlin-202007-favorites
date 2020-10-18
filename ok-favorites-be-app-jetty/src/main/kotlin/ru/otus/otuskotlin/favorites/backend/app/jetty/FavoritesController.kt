package ru.otus.otuskotlin.favorites.backend.app.jetty

import ru.otus.otuskotlin.favorites.mp.transport.models.item.*
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("favorites")
class FavoritesController {

    private val service = JettyFavoritesService()

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("get")
    fun get(query: KmpFavoritesItemGet): KmpFavoritesItemResponse = service.get(query)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("index")
    fun index(query: KmpFavoritesItemIndex): KmpFavoritesItemResponseIndex = service.index(query)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("put")
    fun create(query: KmpFavoritesItemPut): KmpFavoritesItemResponse = service.put(query)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update")
    fun update(query: KmpFavoritesItemUpdate): KmpFavoritesItemResponse = service.update(query)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("remove")
    fun remove(query: KmpFavoritesItemRemove): KmpFavoritesItemResponse = service.remove(query)
}
