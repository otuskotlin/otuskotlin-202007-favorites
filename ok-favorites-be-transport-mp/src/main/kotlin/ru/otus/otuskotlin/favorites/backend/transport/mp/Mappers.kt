package ru.otus.otuskotlin.favorites.backend.transport.mp

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesError
import ru.otus.otuskotlin.favorites.backend.common.model.FavoritesItemModel
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesError
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResultStatuses
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*

fun FavoritesItemContext.setQuery(save: KmpFavoritesItemPut) = this.apply {
    requestFavoritesItem = save.model()
}

fun FavoritesItemContext.setQuery(save: KmpFavoritesItemUpdate) = this.apply {
    requestFavoritesItem = save.model()
}

fun FavoritesItemContext.setQuery(get: KmpFavoritesItemGet) = this.apply {
    requestUserId = get.userId ?: ""
    requestEntityType = get.entityType ?: ""
    requestEntityId = get.entityId ?: ""
}

fun FavoritesItemContext.setQuery(del: KmpFavoritesItemRemove) = this.apply {
    requestUserId = del.userId ?: ""
    requestEntityType = del.entityType ?: ""
    requestEntityId = del.entityId ?: ""
}

fun FavoritesItemContext.setQuery(index: KmpFavoritesItemIndex) = this.apply {
//    filter = index.filter ?: UserModel.Filter
}

fun FavoritesItemContext.resultItem(): KmpFavoritesItemResponse = KmpFavoritesItemResponse(
    data = responseFavoritesItem.kmp(),
    errors = errors.map { it.kmp() },
    status = kmpStatus()
)

fun FavoritesItemContext.resultIndex(): KmpFavoritesItemIndex = KmpFavoritesItemIndex(
    data = listOf(responseFavoritesItem.kmp()),
    errors = errors.map { it.kmp() },
    status = KmpFavoritesResultStatuses.SUCCESS
)

fun FavoritesItemContext.kmpStatus() = when {
    status.isError || errors.any { it.level.isError } -> KmpFavoritesResultStatuses.ERROR
    errors.any { it.level.isWarning } -> KmpFavoritesResultStatuses.WARNING
    else -> KmpFavoritesResultStatuses.SUCCESS
}

fun KmpFavoritesItemSave.model() = FavoritesItemModel(
    userId = userId.modelToString(),
    entityType = entityType.modelToString(),
    entityId = entityId.modelToString(),
    description = description.modelToString(),
    uri = uri.modelToString()

)

private fun String?.modelToString() = this?.takeIf { it.isNotBlank() } ?: ""

private fun String.kmpToString() = this.takeIf { it.isNotBlank() }

fun FavoritesItemModel.kmp() = KmpFavoritesItem(
    userId = userId.kmpToString(),
    entityType = entityType.kmpToString(),
    entityId = entityId.kmpToString(),
    description = description.kmpToString(),
    uri = uri.kmpToString()
)

fun FavoritesError.kmp() = KmpFavoritesError(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it != FavoritesError.Groups.NONE }?.toString(),
    field = field.takeIf { it.isNotBlank() },
    level = level.kmp(),
    message = message.takeIf { it.isNotBlank() }
)

fun FavoritesError.Levels.kmp() = when (this) {
    FavoritesError.Levels.ERROR, FavoritesError.Levels.FATAL -> KmpFavoritesError.Level.ERROR
    else -> KmpFavoritesError.Level.SUCCESS
}

