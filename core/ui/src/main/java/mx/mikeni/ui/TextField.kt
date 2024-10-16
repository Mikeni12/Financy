package mx.mikeni.ui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun OutlinedErrorTextField(
        value: String,
        label: String,
        isError: Boolean,
        errorMessage: String,
        modifier: Modifier = Modifier,
        onValueChangeListener: (String) -> Unit
) {
    OutlinedTextField(
            value = value,
            onValueChange = onValueChangeListener,
            label = { Text(label) },
            singleLine = true,
            shape = CircleShape,
            isError = isError,
            supportingText = { if (isError) Text(errorMessage) },
            trailingIcon = {
                if (isError) {
                    Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = null
                    )
                }
            },
            modifier = modifier
    )
}

@Composable
fun OutlinedErrorPasswordTextField(
        value: String,
        label: String,
        isPasswordVisible: Boolean,
        isError: Boolean,
        errorMessage: String,
        modifier: Modifier = Modifier,
        onValueChangeListener: (String) -> Unit,
        onPasswordVisibilityListener: () -> Unit,
) {
    OutlinedTextField(
            value = value,
            onValueChange = onValueChangeListener,
            label = { Text(label) },
            singleLine = true,
            shape = CircleShape,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = isError,
            supportingText = { if (isError) Text(errorMessage) },
            trailingIcon = {
                IconButton(
                        onClick = onPasswordVisibilityListener
                ) {
                    Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                    )
                }
            },
            modifier = modifier
    )
}
