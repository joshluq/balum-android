package pe.joshluq.balum.feature.credentialrecovery

sealed interface CredentialRecoveryState

object InitialState : CredentialRecoveryState

object LoadingState : CredentialRecoveryState

object RecoverSuccessfulState : CredentialRecoveryState

class ErrorState(val message: String) : CredentialRecoveryState

class EmailErrorState(val message: String) : CredentialRecoveryState
