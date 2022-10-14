package pe.joshluq.balum.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.joshluq.balum.R
import pe.joshluq.balum.common.navigation.SignUpNavigator
import pe.joshluq.balum.ui.theme.BalumTheme
import pe.joshluq.balum.ui.theme.LightBlue1
import pe.joshluq.balum.ui.widget.*

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, navigator: SignUpNavigator = SignUpNavigator) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlue1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(Modifier.weight(2f))
            Form(Modifier.weight(4f), navigator)
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    BalumTheme {
        SignUpScreen()
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.signup_title),
            modifier = Modifier.padding(top = 32.dp),
            style = MaterialTheme.typography.h5
        )
        Image(
            painter = painterResource(id = R.drawable.img_signup),
            contentDescription = null,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
private fun Form(modifier: Modifier = Modifier, navigator: SignUpNavigator = SignUpNavigator) {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NameTextField(
            value = name,
            label = stringResource(R.string.signup_name_label),
            onValueChange = { newText ->

                name = newText
            },
            imeAction = ImeAction.Next,

            )
        NameTextField(
            value = lastname,
            label = stringResource(R.string.signup_lastname_label),
            onValueChange = { newText ->

                lastname = newText
            },
            imeAction = ImeAction.Next,

            )
        EmailTextField(
            value = email,
            onValueChange = { newText ->

                email = newText
            },
            imeAction = ImeAction.Next,

            )
        PasswordTextField(
            value = password,
            onValueChange = { newText ->

                password = newText
            },
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
        )
        ButtonContainer(
            onSignUpButtonClick = {

            },
            onSignInLinkClick = {
                navigator.navigateToSignIn()
            }
        )
    }
}


@Composable
private fun ButtonContainer(
    modifier: Modifier = Modifier,
    onSignUpButtonClick: () -> Unit,
    onSignInLinkClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        PrimaryButton(
            text = stringResource(R.string.signup_signup_button),
            onClick = onSignUpButtonClick
        )

        Spacer(Modifier.height(12.dp))
        LinkText(
            onClick = {
                onSignInLinkClick()
            },
            text = stringResource(R.string.signup_signin_button),
            linkText = stringResource(R.string.signup_signin_button_link),
        )
        Spacer(Modifier.height(32.dp))
    }
}
