package pe.joshluq.balum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.joshluq.balum.common.navigation.Destination
import pe.joshluq.balum.feature.signin.SignInScreen
import pe.joshluq.balum.feature.welcome.WelcomeScreen
import pe.joshluq.balum.ui.theme.BalumTheme

typealias Navigable = ((Destination) -> Unit)?

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Destination.Welcome.route) {
                composable(Destination.Welcome.route) {
                    WelcomeScreen(onNavigate = navController::runNavigation)
                }
                composable(Destination.SignIn.route) { SignInScreen() }
            }
        }
    }
}

private fun NavHostController.runNavigation(destination: Destination) {
    navigate(destination.route)
}

@Composable
fun BalumBlueScreen(isForPreview: Boolean = false, content: @Composable () -> Unit) {
    BalumTheme(isForPreview) {
        val background = MaterialTheme.colors.primary
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = background,
            content = content
        )
    }
}

@Composable
fun BalumScreen(isForPreview: Boolean = false, content: @Composable () -> Unit) {
    BalumTheme(isForPreview) {
        val background = MaterialTheme.colors.background
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = background,
            content = content
        )
    }
}