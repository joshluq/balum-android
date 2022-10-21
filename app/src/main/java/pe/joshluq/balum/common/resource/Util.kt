package pe.joshluq.balum.common.resource

import pe.joshluq.balum.R

fun provideErrorMessage(
    resourceProvider: ResourceProvider,
    throwable: Throwable
) = throwable.message ?: resourceProvider.getString(R.string.default_message_error)