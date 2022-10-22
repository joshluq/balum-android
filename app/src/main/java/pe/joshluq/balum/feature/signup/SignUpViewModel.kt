package pe.joshluq.balum.feature.signup

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
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.Profile
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.usecase.SignUpUseCase
import pe.joshluq.balum.domain.usecase.UseCase
import pe.joshluq.balum.domain.validation.Field
import pe.joshluq.balum.domain.validation.onDefaultError
import pe.joshluq.balum.domain.validation.onEmptyFieldError
import pe.joshluq.balum.domain.validation.onInvalidFormatError
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @Named("signUpUseCase") private val signInUseCase: UseCase<SignUpUseCase.Params, User>,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var state by mutableStateOf<SignUpState>(InitialState)
        private set

    fun createAccount(name: String, lastname: String, email: String, password: String) =
        viewModelScope.launch {
            state = LoadingState
            val profile = Profile(name, lastname, email)
            val credential = Credential(password = password)
            val params = SignUpUseCase.Params(profile, credential)
            signInUseCase(params)
                .onSuccess(::onSingInSuccess)
                .onFailure(::onSignInFailure)
        }

    private fun onSingInSuccess(user: User) {
        state = CreateUserSuccessfulState
    }

    private fun onSignInFailure(throwable: Throwable) = throwable
        .onEmptyFieldError(::onFieldEmptyError)
        .onInvalidFormatError(::onFieldFormatError)
        .onDefaultError(::showMessage)

    private fun showMessage(throwable: Throwable) {
        state = ErrorState(provideErrorMessage(resourceProvider, throwable))
    }

    private fun onFieldEmptyError(field: Field) {
        if (field == Field.NAME) {
            state = NameErrorState(resourceProvider.getString(R.string.signup_name_field_required_message))
            return
        }
        if (field == Field.LAST_NAME) {
            state = LastNameErrorState(resourceProvider.getString(R.string.signup_lastname_field_required_message))
            return
        }
        if (field == Field.EMAIL) {
            state = EmailErrorState(resourceProvider.getString(R.string.signup_email_field_required_message))
            return
        }
        if (field == Field.PASSWORD) {
            state = PasswordErrorState(resourceProvider.getString(R.string.signup_password_field_required_message))
            return
        }
    }

    private fun onFieldFormatError(field: Field) {
        if (field == Field.EMAIL) {
            state = EmailErrorState(resourceProvider.getString(R.string.signup_email_field_required_message))
            return
        }
        if (field == Field.PASSWORD) {
            state = PasswordErrorState(resourceProvider.getString(R.string.signup_password_field_required_message))
            return
        }
    }

    fun clearError() {
        state = InitialState
    }

}