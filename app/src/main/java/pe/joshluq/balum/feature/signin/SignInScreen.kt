package pe.joshluq.balum.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import pe.joshluq.balum.R
import pe.joshluq.balum.ui.theme.BalumTheme
import pe.joshluq.balum.ui.theme.LightBlue1
import pe.joshluq.balum.ui.widget.*

@Composable
fun SignInScreen(modifier: Modifier = Modifier, viewModel: SignInViewModel = hiltViewModel()) {
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
            Form(Modifier.weight(3f), viewModel)
        }
        if (viewModel.state.isLoading) {
            LoadingDialog {}
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    BalumTheme {
        SignInScreen()
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
            text = stringResource(R.string.signin_title),
            modifier = Modifier.padding(top = 32.dp),
            style = MaterialTheme.typography.h5
        )
        Image(
            painter = painterResource(id = R.drawable.img_signin),
            contentDescription = null,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
private fun Form(modifier: Modifier = Modifier, viewModel: SignInViewModel = hiltViewModel()) {
    var username by remember { mutableStateOf("") }
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
        EmailTextField(
            value = username,
            onValueChange = { newText ->
                viewModel.clearEmailError()
                username = newText
            },
            imeAction = ImeAction.Next,
            errorMessage = ErrorMessage(viewModel.state.onUsernameError)
        )
        PasswordTextField(
            value = password,
            onValueChange = { newText ->
                viewModel.clearPasswordError()
                password = newText
            },
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.signIn(username, password)
                    focusManager.clearFocus()
                }
            ),
            errorMessage = ErrorMessage(viewModel.state.onPasswordError)
        )
        ButtonContainer(onClick = {
            viewModel.signIn(username, password)
        })
    }
}


@Composable
private fun ButtonContainer(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        PrimaryButton(text = stringResource(R.string.signin_signin_button), onClick = onClick)

        Spacer(Modifier.height(12.dp))
        LinkText(
            onClick = {

            },
            text = stringResource(R.string.signin_signup_button),
            linkText = stringResource(R.string.signin_signup_button_link),
        )
        Spacer(Modifier.height(32.dp))
    }
}
