package pe.joshluq.balum.common.navigation

import androidx.navigation.navOptions


sealed interface WelcomeNavigator {

    fun navigateToSignIn()

    fun navigateToSignUp()

    companion object : WelcomeNavigator {

        private var navigationActions: NavigationActions? = null

        fun build(navigationActions: NavigationActions): WelcomeNavigator {
            this.navigationActions = navigationActions
            return this
        }

        override fun navigateToSignIn() {
            navigationActions?.navigateToSignIn()
        }

        override fun navigateToSignUp() {
            navigationActions?.navigateToSignUp()
        }

    }
}

sealed interface SignInNavigator {

    fun navigateToSignUp()

    fun navigateToForgotPassword()

    companion object : SignInNavigator {

        private var navigationActions: NavigationActions? = null

        fun build(navigationActions: NavigationActions): SignInNavigator {
            this.navigationActions = navigationActions
            return this
        }

        override fun navigateToSignUp() {
            val options = navOptions {
                popUpTo(Destination.Welcome.route) { inclusive = false }
            }
            navigationActions?.navigateToSignUp(options)
        }

        override fun navigateToForgotPassword() {
            navigationActions?.navigateToForgotPassword()
        }

    }
}

sealed interface SignUpNavigator {

    fun navigateToSignIn()

    companion object : SignUpNavigator {
        private var navigationActions: NavigationActions? = null

        fun build(navigationActions: NavigationActions): SignUpNavigator {
            this.navigationActions = navigationActions
            return this
        }

        override fun navigateToSignIn() {
            val options = navOptions {
                popUpTo(Destination.Welcome.route) { inclusive = false }
            }
            navigationActions?.navigateToSignIn(options)
        }
    }
}

sealed interface CredentialRecoveryNavigator {

    fun navigateToBack()

    fun navigateToSuccess()

    companion object : CredentialRecoveryNavigator {
        private var navigationActions: NavigationActions? = null

        fun build(navigationActions: NavigationActions): CredentialRecoveryNavigator {
            this.navigationActions = navigationActions
            return this
        }

        override fun navigateToBack() {
            navigationActions?.navigateToBack()
        }

        override fun navigateToSuccess() {
            val options = navOptions {
                popUpTo(Destination.SignIn.route) { inclusive = false }
            }
            navigationActions?.navigateToResetPasswordSuccessful(options)
        }
    }
}
