package ru.otus.otuskotlin.favorites.backend.logics

import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContext
import ru.otus.otuskotlin.favorites.backend.common.FavoritesItemContextStatus
import ru.otus.otuskotlin.favorites.backend.common.errors.GeneralError
import ru.otus.otuskotlin.favorites.backend.logics.handlers.responsePrepareHandler
import ru.otus.otuskotlin.favorites.backend.logics.handlers.stubRemove
import ru.otus.otuskotlin.favorites.mp.cor.cor


class FavoritesItemRemoveChain(
//        private val userRepoProd: IUserRepository,
//        private val userRepoTest: IUserRepository
) {

    suspend fun exec(context: FavoritesItemContext) = FavoritesItemRemoveChain.chain.exec(context.apply {
//        userRepoProd = this@FavoritesItemGetChain.userRepoProd
//        userRepoTest = this@FavoritesItemGetChain.userRepoTest
    })

    companion object {
        private val chain = cor<FavoritesItemContext> {
            // Инициализация пайплайна
            exec { status = FavoritesItemContextStatus.RUNNING }

            // Валидация
            // Обработка запроса
//            processor {
//                exec(querySetWorkMode)
//            }

            exec(stubRemove) // Обработка стабов

            // Обработка и работа с БД
            handler {
                isApplicable { status == FavoritesItemContextStatus.RUNNING }
                exec {
                    try {
                        //responseUser = userRepo.get(requestUserId)
                    } catch (e: Throwable) {
                        status = FavoritesItemContextStatus.FAILING
                        errors.add(GeneralError(code = "repo-get-error", e = e))
                    }
                }
            }

            // Подготовка ответа
            // Подготовка ответа
            exec(responsePrepareHandler)
        }
    }
}
