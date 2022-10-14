package pe.joshluq.balum.common.navigation

import androidx.navigation.navOptions


interface WelcomeNavigator {

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

interface SignInNavigator {

    fun navigateToSignUp()

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

    }
}

interface SignUpNavigator {

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
