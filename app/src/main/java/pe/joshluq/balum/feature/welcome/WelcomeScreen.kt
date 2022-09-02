package pe.joshluq.balum.feature.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.joshluq.balum.R
import pe.joshluq.balum.Screen

@Composable
fun WelcomeScreen(isForPreview: Boolean = false) {
    Screen(isForPreview) {
        Column(modifier = Modifier.fillMaxHeight()) {
            BalumLogo()
            ButtonContainer()
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
            text = "Welcome to",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = "Balum",
            fontSize = 40.sp
        )
    }
}

@Composable
private fun ButtonContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Create Account")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Don’t have an account?")
        }
    }
}