package pe.joshluq.balum.feature.credentialrecovery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.joshluq.balum.R
import pe.joshluq.balum.common.resource.ResourceProvider
import pe.joshluq.balum.common.resource.provideErrorMessage
import pe.joshluq.balum.domain.usecase.RecoverCredentialUseCase
import pe.joshluq.balum.domain.usecase.UseCase
import pe.joshluq.balum.domain.validation.Field
import pe.joshluq.balum.domain.validation.onDefaultError
import pe.joshluq.balum.domain.validation.onEmptyFieldError
import pe.joshluq.balum.domain.validation.onInvalidFormatError
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CredentialRecoveryViewModel @Inject constructor(
    @Named("recoverCredentialUseCase") private val recoverCredentialUseCase: UseCase<RecoverCredentialUseCase.Params, Unit>,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var state by mutableStateOf<CredentialRecoveryState>(InitialState)
        private set

    var email by mutableStateOf("")

    fun recoverCredential() = viewModelScope.launch {
        state = LoadingState
        val params = RecoverCredentialUseCase.Params(email)
        recoverCredentialUseCase(params)
            .onSuccess(::onSingInSuccess)
            .onFailure(::onSignInFailure)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onSingInSuccess(unit: Unit) {
        state = RecoverSuccessfulState
    }

    private fun onSignInFailure(throwable: Throwable) = throwable
        .onInvalidFormatError(::onFieldFormatError)
        .onEmptyFieldError(::onFieldEmptyError)
        .onDefaultError(::showMessage)

    private fun showMessage(throwable: Throwable) {
        state = ErrorState(provideErrorMessage(resourceProvider, throwable))
    }

    private fun onFieldEmptyError(field: Field) {
        if (field == Field.EMAIL) {
            state = EmailErrorState(
                resourceProvider.getString(R.string.signin_email_field_required_message)
            )
            return
        }
    }

    private fun onFieldFormatError(field: Field) {
        if (field == Field.EMAIL) {
            state = EmailErrorState(
                resourceProvider.getString(R.string.signin_email_field_invalid_message)
            )
            return
        }
    }

    fun resetState() {
        state = InitialState
    }
}