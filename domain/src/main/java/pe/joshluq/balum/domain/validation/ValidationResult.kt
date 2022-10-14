package pe.joshluq.balum.domain.validation

sealed class ValidationResult(
    val field: Field = Field.NONE, message: String? = null,
) : Throwable(message) {
    class EmptyField(field: Field = Field.NONE) : ValidationResult(field)
    class TextSize(field: Field = Field.NONE) : ValidationResult(field)
    class InvalidFormat(field: Field = Field.NONE) : ValidationResult(field)
}

inline fun Throwable.onEmptyFieldError(result: (field: Field) -> Unit) = when (this) {
    is ValidationResult.EmptyField -> {
        result(field)
        this
    }
    else -> this
}

inline fun Throwable.onSizeError(result: (field: Field) -> Unit) = when (this) {
    is ValidationResult.TextSize -> {
        result(field)
        this
    }
    else -> this
}

inline fun Throwable.onInvalidFormatError(result: (field: Field) -> Unit) = when (this) {
    is ValidationResult.InvalidFormat -> {
        result(field)
        this
    }
    else -> this
}

fun Throwable.onDefaultError(result: (Throwable) -> Unit) {
    if (this !is ValidationResult) result(this)
}
