package ru.otus.otuskotlin.favorites.backend.logics

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext


class FavoritesItemCrud(
//        userRepoProd: IUserRepository = IUserRepository.NONE,
//        userRepoTest: IUserRepository = IUserRepository.NONE
) {
    private val getChain = FavoritesItemGetChain()
    private val indexChain = FavoritesItemIndexChain()
    private val putChain = FavoritesItemPutChain() //userRepoProd = userRepoProd, userRepoTest = userRepoTest
    private val updateChain = FavoritesItemUpdateChain()
    private val removeChain = FavoritesItemRemoveChain()

    suspend fun get(context: FavoritesItemContext) = getChain.exec(context)
    suspend fun index(context: FavoritesItemContext) = indexChain.exec(context)
    suspend fun put(context: FavoritesItemContext) = putChain.exec(context)
    suspend fun update(context: FavoritesItemContext) = updateChain.exec(context)
    suspend fun remove(context: FavoritesItemContext) = removeChain.exec(context)
}
