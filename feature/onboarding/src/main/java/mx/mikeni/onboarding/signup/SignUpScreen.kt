package mx.mikeni.onboarding.signup

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpScreen(
        modifier: Modifier = Modifier,
        onButtonListener: () -> Unit
) {
    Text("SignUp")
    Button(
            onClick = onButtonListener,
            modifier = Modifier.wrapContentSize()
    ) {
        Text("Sign In")
    }
}
