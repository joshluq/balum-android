package pe.joshluq.balum.common.navigation


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

        }

    }
}
