package mx.mikeni.onboarding.signup.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mx.mikeni.ui.Space64

@Composable
fun ColumnScope.UserFormScreen(
        email: String,
        password: String,
        name: String,
        lastName: String,
        onEmailChangedListener: (String) -> Unit,
        onPasswordChangedListener: (String) -> Unit,
        onNameChangedListener: (String) -> Unit,
        onLastNameChangedListener: (String) -> Unit,
        onNextListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Text(
            text = "Fill the form to start your registration",
            style = MaterialTheme.typography.titleLarge,
    )
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
    Button(
            onClick = onNextListener,
            enabled = email.isNotBlank() && password.isNotBlank() && name.isNotBlank() && lastName.isNotBlank(),
            modifier = Modifier.align(Alignment.End).padding(end = Space64)
    ) {
        Text("Next")
    }
}
