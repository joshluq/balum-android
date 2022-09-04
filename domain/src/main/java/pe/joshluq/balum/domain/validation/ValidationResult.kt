package pe.joshluq.balum.domain.validation

sealed class ValidationResult(
    val field: Field? = null, message: String? = null,
) : Throwable(message) {
    class EmptyField(field: Field? = null) : ValidationResult(field)
    class TextSize(field: Field? = null) : ValidationResult(field)
    class InvalidFormat(field: Field? = null) : ValidationResult(field)
}

inline fun Throwable.onEmptyFieldError(result: () -> Unit) = when (this) {
    is ValidationResult.EmptyField -> {
        result()
        this
    }
    else -> this
}

inline fun Throwable.onSizeError(result: () -> Unit) = when (this) {
    is ValidationResult.TextSize -> {
        result()
        this
    }
    else -> this
}

inline fun Throwable.onInvalidFormatError(result: () -> Unit) = when (this) {
    is ValidationResult.InvalidFormat -> {
        result()
        this
    }
    else -> this
}

fun Throwable.onDefaultError(result: (Throwable) -> Unit) {
    if (this !is ValidationResult) result(this)
}
