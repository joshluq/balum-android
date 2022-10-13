package pe.joshluq.balum.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.joshluq.balum.feature.welcome.WelcomeScreen

@Composable
fun BalumNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Destination = Destination.Welcome,
    navAction: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        composable(Destination.Welcome.route) {
            WelcomeScreen(onNavigate = {
                navAction.navigateToSignIn()
            })
        }
    }
}

