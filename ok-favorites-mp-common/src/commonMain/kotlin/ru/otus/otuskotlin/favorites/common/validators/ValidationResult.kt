package ru.otus.otuskotlin.favorites.common.validators

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

data class ValidationResult
@JvmOverloads
constructor (
    val errors: List<HandleError> = emptyList()
) {
    constructor(vararg argErrors: HandleError?): this(errors = argErrors.filterNotNull())

    val level: HandleError.Level
        get() = errors.maxBy { it.level.lvl }?.level ?: HandleError.Level.NONE

    val isOk
        get() = level.lvl < HandleError.Level.ERROR.lvl

    companion object {
        @JvmStatic
        @get:JvmName("getSUCCESS")
        val SUCCESS = ValidationResult()

        @JvmField
        val OK = SUCCESS
    }
}
