package ru.otus.otuskotlin.favorites.mp.transport.validators

import ru.otus.otuskotlin.favorites.mp.transport.models.item.KmpFavoritesItemUpdate
import ru.otus.otuskotlin.favorites.mp.validators.ValidationResult
import ru.otus.otuskotlin.favorites.mp.validators.Validator
import ru.otus.otuskotlin.favorites.mp.validators.fields.ValidatorId
import ru.otus.otuskotlin.favorites.mp.validators.fields.ValidatorLastName


class KmpValidatorFavoritesItemUpdate: Validator<KmpFavoritesItemUpdate> {

    private val validatorId = ValidatorId()
    private val validatorLName = ValidatorLastName()

    override fun validate(arg: KmpFavoritesItemUpdate): ValidationResult = ValidationResult(
        //todo: validation
        *validatorId.validate(arg.userId).errors.toTypedArray(),
        *validatorLName.validate(arg.uri).errors.toTypedArray()
    )
}
