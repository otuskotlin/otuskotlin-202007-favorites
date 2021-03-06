package ru.otus.otuskotlin.favorites.backend.transport.mp

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.model.*
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesError
import ru.otus.otuskotlin.favorites.mp.transport.models.KmpFavoritesResultStatuses
import ru.otus.otuskotlin.favorites.mp.transport.models.item.*

fun FavoritesItemContext.setQuery(save: KmpFavoritesItemSave) = this.apply {
    requestUserId = save.userId ?: ""
    requestEntityType = save.entityType ?: ""
    requestEntityId = save.entityId ?: ""

    requestFavoritesItem = save.model()

    when(save) {
        is KmpFavoritesItemUpdate -> {
            stubUpdateCase = when (save.debug?.stub) {
                KmpFavoritesItemUpdate.StubCases.SUCCESS -> FavoritesUpdateStubCases.SUCCESS
                else -> FavoritesUpdateStubCases.NONE
            }
            //workMode = save.debug?.db?.model() ?: WorkModes.DEFAULT
        }
        is KmpFavoritesItemPut -> {
            stubPutCase = when (save.debug?.stub) {
                KmpFavoritesItemPut.StubCases.SUCCESS -> FavoritesPutStubCases.SUCCESS
                else -> FavoritesPutStubCases.SUCCESS
            }
            //workMode = save.debug?.db?.model() ?: WorkModes.DEFAULT
        }
    }
}

fun FavoritesItemContext.setQuery(get: KmpFavoritesItemGet) = this.apply {
    requestUserId = get.userId ?: ""
    requestEntityType = get.entityType ?: ""
    requestEntityId = get.entityId ?: ""
    requestFavoritesItem.entityId = get.entityId ?: ""
    requestFavoritesItem.userId = get.userId ?: ""
    requestFavoritesItem.entityType = get.entityType ?: ""
}

fun FavoritesItemContext.setQuery(del: KmpFavoritesItemRemove) = this.apply {
    requestUserId = del.userId ?: ""
    requestEntityType = del.entityType ?: ""
    requestEntityId = del.entityId ?: ""
    requestFavoritesItem.entityId = del.entityId ?: ""
    requestFavoritesItem.userId = del.userId ?: ""
    requestFavoritesItem.entityType = del.entityType ?: ""
}

fun FavoritesItemContext.setQuery(index: KmpFavoritesItemIndex) = this.apply {
    requestFavoritesItemFilter = index.filter?.toModel() ?: FavoritesItemIndexFilter.NONE
    stubIndexCase = when(index.debug?.stub) {
        KmpFavoritesItemIndex.StubCases.SUCCESS -> FavoritesIndexStubCases.SUCCESS
        else -> FavoritesIndexStubCases.NONE
    }
}

private fun KmpFavoritesItemIndex.Filter.toModel(): FavoritesItemIndexFilter = FavoritesItemIndexFilter(
    searchString = searchString ?: "",
    entityType = entityType
)

fun FavoritesItemContext.resultItem(): KmpFavoritesItemResponse = KmpFavoritesItemResponse(
    data = responseFavoritesItem.kmp(),
    errors = errors.map { it.kmp() },
    status = kmpStatus()
)

fun FavoritesItemContext.resultIndex(): KmpFavoritesItemResponseIndex = KmpFavoritesItemResponseIndex(
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

