package ru.otus.otuskotlin.favorites.backend.common.model


data class FavoritesItemModel (
    var userId: String = "",
    var entityType: String = "",
    var entityId: String = "",
    var description: String = "",
    var uri: String = ""
) {
    companion object {
        val NONE = FavoritesItemModel()
    }
}
