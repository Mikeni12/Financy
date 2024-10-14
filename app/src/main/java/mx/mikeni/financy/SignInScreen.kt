package mx.mikeni.financy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import mx.mikeni.financy.ui.theme.Space16
import mx.mikeni.financy.ui.theme.Space4

@Composable
fun SignInScreen(
        onSignInListener: () -> Unit,
        onSignUpListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
    ) {
        Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(Space16))
        Spacer(modifier = Modifier.height(Space16))
        Spacer(modifier = Modifier.height(Space16))
        OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                shape = CircleShape,
                modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                shape = CircleShape,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible }
                    ) {
                        Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = null
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
        )
        Button(
                onClick = onSignInListener,
                modifier = Modifier.wrapContentSize()
        ) {
            Text("Sign In")
        }
        Row(
                horizontalArrangement = Arrangement.spacedBy(Space4),
        ) {
            Text("Don't have an account?")
            Text(
                    text = "Sign Up",
                    modifier = Modifier.clickable(onClick = onSignUpListener)
            )
        }
    }
}
