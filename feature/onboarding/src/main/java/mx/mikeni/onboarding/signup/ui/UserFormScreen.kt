package mx.mikeni.onboarding.signup.ui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserFormScreen(
        email: String,
        password: String,
        name: String,
        lastName: String,
        onEmailChangedListener: (String) -> Unit,
        onPasswordChangedListener: (String) -> Unit,
        onNameChangedListener: (String) -> Unit,
        onLastNameChangedListener: (String) -> Unit,
        modifier: Modifier = Modifier
) {
    OutlinedTextField(
            value = email,
            onValueChange = onEmailChangedListener,
            label = { Text("Email") },
            singleLine = true,
            shape = CircleShape,
            modifier = modifier
    )
    OutlinedTextField(
            value = password,
            onValueChange = onPasswordChangedListener,
            label = { Text("Password") },
            singleLine = true,
            shape = CircleShape,
            modifier = modifier
    )
    OutlinedTextField(
            value = name,
            onValueChange = onNameChangedListener,
            label = { Text("Name") },
            singleLine = true,
            shape = CircleShape,
            modifier = modifier
    )
    OutlinedTextField(
            value = lastName,
            onValueChange = onLastNameChangedListener,
            label = { Text("Last Name") },
            singleLine = true,
            shape = CircleShape,
            modifier = modifier
    )
}
