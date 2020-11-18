package ru.otus.otuskotlin.favorites.backend.common.model

data class FavoritesItemIndexFilter(
        val searchString: String = "",
        val entityType: String? = null
) {
    companion object {
        val NONE = FavoritesItemIndexFilter()
    }
}
