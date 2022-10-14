package pe.joshluq.balum.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.joshluq.balum.R
import pe.joshluq.balum.common.resource.ResourceProvider
import pe.joshluq.balum.common.uimodel.UiError
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.usecase.UseCase
import pe.joshluq.balum.domain.validation.*
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SignInViewModel @Inject constructor(
    @Named("signInUseCase") private val signInUseCase: UseCase<Credential, User>,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var state by mutableStateOf(SignInState())
        private set

    fun signIn(username: String, password: String) = viewModelScope.launch {
        state = state.copy(
            isLoading = true
        )
        val credential = Credential(username, password)
        signInUseCase(credential)
            .onSuccess(::onSingInSuccess)
            .onFailure(::onSignInFailure)
    }

    private fun onSingInSuccess(user: User) {
        state = state.copy(
            onSignInSuccessful = true,
            isLoading = false
        )
    }

    private fun onSignInFailure(throwable: Throwable) = throwable
        .onInvalidFormatError(::onEmailFormatError)
        .onEmptyFieldError(::onEmailEmptyError)
        .onDefaultError(::showMessage)

    private fun showMessage(throwable: Throwable) {
        state = state.copy(
            onSignInSuccessful = false,
            isLoading = false,
            showError = UiError(message = provideErrorMessage(resourceProvider, throwable))
        )
    }

    private fun onEmailEmptyError(field: Field) {
        if (field == Field.EMAIL) {
            state = state.copy(
                onSignInSuccessful = false,
                isLoading = false,
                onUsernameError = resourceProvider.getString(R.string.signin_email_field_required_message)
            )
            return
        }
        if (field == Field.PASSWORD) {
            state = state.copy(
                onSignInSuccessful = false,
                isLoading = false,
                onPasswordError = resourceProvider.getString(R.string.signin_password_field_required_message)
            )
            return
        }
    }

    private fun onEmailFormatError(field: Field) {
        if (field == Field.EMAIL) {
            state = state.copy(
                onSignInSuccessful = false,
                isLoading = false,
                onUsernameError = resourceProvider.getString(R.string.signin_email_field_invalid_message)
            )
            return
        }
        if (field == Field.PASSWORD) {
            state = state.copy(
                onSignInSuccessful = false,
                isLoading = false,
                onPasswordError = resourceProvider.getString(R.string.signin_password_field_invalid_message)
            )
            return
        }
    }

    private fun provideErrorMessage(
        resourceProvider: ResourceProvider,
        throwable: Throwable
    ) = throwable.message ?: resourceProvider.getString(R.string.default_message_error)

    fun clearEmailError() {
        state = state.copy(
            onUsernameError = resourceProvider.getString(R.string.default_empty)
        )
    }

    fun clearPasswordError() {
        state = state.copy(
            onPasswordError = resourceProvider.getString(R.string.default_empty)
        )
    }
}