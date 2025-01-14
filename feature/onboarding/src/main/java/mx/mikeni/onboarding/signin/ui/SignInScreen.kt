package mx.mikeni.onboarding.signin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch
import mx.mikeni.ui.ErrorSnackBar
import mx.mikeni.ui.OutlinedErrorPasswordTextField
import mx.mikeni.ui.OutlinedErrorTextField
import mx.mikeni.ui.Space32
import mx.mikeni.ui.Space4
import mx.mikeni.ui.Space64
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
        onSignInListener: (String) -> Unit,
        onSignUpListener: () -> Unit,
        modifier: Modifier = Modifier,
        viewModel: SignInViewModel = koinViewModel<SignInViewModel>()
) {
    val signInUiModel by viewModel.signInUiModel.collectAsState()
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    Scaffold(
            snackbarHost = {
                SnackbarHost(snackBarHostState) {
                    ErrorSnackBar(message = it.visuals.message)
                }
            },
            modifier = modifier
    ) { paddingValues ->
        SignInContent(
                email = email,
                password = password,
                isPasswordVisible = isPasswordVisible,
                isEmailError = signInUiModel.isEmailError,
                isPasswordError = signInUiModel.isPasswordError,
                showProgress = signInUiModel.showProgress,
                onEmailChangedListener = { email = it },
                onPasswordChangedListener = { password = it },
                onPasswordVisibilityListener = { isPasswordVisible = !isPasswordVisible },
                onSignInListener = {
                    focusManager.clearFocus()
                    viewModel.signIn(email, password)
                },
                onSignUpListener = onSignUpListener,
                modifier = modifier
                        .padding(paddingValues)
                        .padding(Space32)
        )
        with(signInUiModel) {
            when {
                userId != null -> onSignInListener(userId)
                error != null -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(error.message.orEmpty())
                    }
                }
            }
        }
    }
}

@Composable
private fun SignInContent(
        email: String,
        password: String,
        isPasswordVisible: Boolean,
        isEmailError: Boolean,
        isPasswordError: Boolean,
        showProgress: Boolean,
        onEmailChangedListener: (String) -> Unit,
        onPasswordChangedListener: (String) -> Unit,
        onPasswordVisibilityListener: () -> Unit,
        onSignInListener: () -> Unit,
        onSignUpListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Column(
            verticalArrangement = Arrangement.spacedBy(Space64),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
    ) {
        Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(Space32))
        OutlinedErrorTextField(
                value = email,
                label = "Email",
                isError = isEmailError,
                errorMessage = "Invalid email",
                onValueChangeListener = onEmailChangedListener,
                modifier = Modifier.fillMaxWidth()
        )
        OutlinedErrorPasswordTextField(
                value = password,
                label = "Password",
                isPasswordVisible = isPasswordVisible,
                isError = isPasswordError,
                errorMessage = "Invalid password",
                onValueChangeListener = onPasswordChangedListener,
                onPasswordVisibilityListener = onPasswordVisibilityListener,
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Space64))
        Button(
                onClick = onSignInListener,
                modifier = Modifier.wrapContentSize()
        ) {
            if (showProgress) {
                CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.wrapContentSize()
                )
            } else {
                Text("Sign In")
            }
        }
        Row(
                horizontalArrangement = Arrangement.spacedBy(Space4),
        ) {
            Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodyLarge
            )
            Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onSignUpListener)
            )
        }
    }
}
