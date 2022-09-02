package pe.joshluq.balum.feature.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.joshluq.balum.R
import pe.joshluq.balum.Screen

@Composable
fun WelcomeScreen(isForPreview: Boolean = false, onNavigate: ((String) -> Unit)? = null) {
    Screen(isForPreview) {
        Column(modifier = Modifier.fillMaxHeight()) {
            BalumLogo()
            ButtonContainer(onNavigate = onNavigate)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen(true)
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
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 40.sp
        )
    }
}

@Composable
private fun ButtonContainer(modifier: Modifier = Modifier, onNavigate: ((String) -> Unit)?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { onNavigate?.invoke("signin") }) {
            Text(text = stringResource(R.string.welcome_signin_button))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.welcome_signup_button))
        }
    }
}