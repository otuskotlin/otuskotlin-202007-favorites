package ru.otus.otuskotlin.favorites.backend.logics

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.errors.GeneralError
import ru.otus.otuskotlin.favorites.backend.logics.handlers.responsePrepareHandler
import ru.otus.otuskotlin.favorites.backend.logics.handlers.stubPut
import ru.otus.otuskotlin.favorites.backend.logics.handlers.stubUpdate
import ru.otus.otuskotlin.favorites.mp.cor.cor

class FavoritesItemUpdateChain {
//    private val favoritesRepoProd: IFavoritesRepository,
//    private val favoritesRepoTest: IFavoritesRepository


    suspend fun exec(context: FavoritesItemContext) = FavoritesItemUpdateChain.chain.exec(context.apply {
//        userRepoProd = this@UserCreateChain.userRepoProd
//        userRepoTest = this@UserCreateChain.userRepoTest
    })

    companion object {
        private val chain = cor<FavoritesItemContext> {
            //инициализация пайплайна
            exec {status = FavoritesItemContextStatus.RUNNING}

            //валидация
            //обработка запроса
            //processor { exec(querySetWorkMode) }

            // Обработка стабов
            exec(stubUpdate)

            // Обработка и работа с БД
            handler {
                isApplicable { status == FavoritesItemContextStatus.RUNNING }
                exec {
                    try {
                        //responseUser = userRepo.create(requestUser)
                    } catch (e: Throwable) {
                        status = FavoritesItemContextStatus.FAILING
                        errors.add(GeneralError(code = "repo-create-error", e = e))
                    }
                }
            }

            // Подготовка ответа
            exec(responsePrepareHandler)

        }
    }

}
