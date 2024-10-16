package mx.mikeni.onboarding.signup.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mx.mikeni.ui.OutlinedErrorPasswordTextField
import mx.mikeni.ui.OutlinedErrorTextField
import mx.mikeni.ui.Space64

@Composable
fun ColumnScope.UserFormScreen(
        email: String,
        password: String,
        name: String,
        lastName: String,
        isPasswordVisible: Boolean,
        isEmailError: Boolean,
        isPasswordError: Boolean,
        isNameError: Boolean,
        isLastNameError: Boolean,
        onEmailChangedListener: (String) -> Unit,
        onPasswordChangedListener: (String) -> Unit,
        onPasswordVisibilityListener: () -> Unit,
        onNameChangedListener: (String) -> Unit,
        onLastNameChangedListener: (String) -> Unit,
        onNextListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Text(
            text = "Fill the form to start your registration",
            style = MaterialTheme.typography.titleLarge,
    )
    OutlinedErrorTextField(
            value = email,
            label = "Email",
            isError = isEmailError,
            errorMessage = "Invalid email",
            onValueChangeListener = onEmailChangedListener,
            modifier = modifier
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
    OutlinedErrorTextField(
            value = name,
            label = "Name",
            isError = isNameError,
            errorMessage = "Invalid name",
            onValueChangeListener = onNameChangedListener,
            modifier = modifier
    )
    OutlinedErrorTextField(
            value = lastName,
            label = "Last Name",
            isError = isLastNameError,
            errorMessage = "Invalid last name",
            onValueChangeListener = onLastNameChangedListener,
            modifier = modifier
    )
    Button(
            onClick = onNextListener,
            enabled = email.isNotBlank() && password.isNotBlank() && name.isNotBlank() && lastName.isNotBlank(),
            modifier = Modifier.align(Alignment.End).padding(end = Space64)
    ) {
        Text("Next")
    }
}
