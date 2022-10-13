package pe.joshluq.balum.feature.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.joshluq.balum.R
import pe.joshluq.balum.Navigable
import pe.joshluq.balum.common.navigation.Destination
import pe.joshluq.balum.ui.theme.BalumTheme
import pe.joshluq.balum.ui.widget.ButtonType
import pe.joshluq.balum.ui.widget.LinkText
import pe.joshluq.balum.ui.widget.PrimaryButton

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigate: Navigable = null) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            BalumLogo()
            ButtonContainer(onNavigate = onNavigate)
        }
    }
}

@Preview()
@Composable
fun WelcomeScreenPreview() {
    BalumTheme {
        WelcomeScreen()
    }
}

@Composable
private fun BalumLogo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_balum_logo),
            contentDescription = null, // decorative element
        )
        Text(
            text = stringResource(R.string.welcome_logo_label),
            modifier = Modifier.padding(top = 32.dp),
            style = MaterialTheme.typography.h5,
        )
        Text(
            color = Color.White,
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h3,
        )
    }
}

@Composable
private fun ButtonContainer(modifier: Modifier = Modifier, onNavigate: Navigable = null) {

    fun onSignInButton() {
        onNavigate?.invoke(Destination.SignIn)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        PrimaryButton(
            onClick = ::onSignInButton,
            text = stringResource(R.string.welcome_signin_button),
            type = ButtonType.PRIMARY_WHITE,
        )
        Spacer(Modifier.height(12.dp))
        LinkText(
            onClick = {

            },
            text = stringResource(R.string.welcome_signup_button),
            linkText = stringResource(R.string.welcome_signup_button_link),
            color = Color.White,
            linkColor = Color.White
        )
        Spacer(Modifier.height(32.dp))
    }
}


