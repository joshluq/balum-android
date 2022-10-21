package pe.joshluq.balum.domain.validation

import java.util.regex.Pattern
import java.util.regex.Pattern.compile

val emailPattern: Pattern = compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

internal inline fun String.ifEmailError(
    field: Field = Field.EMAIL,
    defaultValue: (ValidationResult) -> Unit
) = when {
    isNullOrEmpty() -> defaultValue(ValidationResult.EmptyField(field))
    matches(emailPattern.toRegex()).not() -> defaultValue(ValidationResult.InvalidFormat(field))
    else -> Unit
}