package mx.mikeni.onboarding.signup.ui

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserSignedScreen(
        modifier: Modifier = Modifier,
        onSignUpListener: () -> Unit
) {
    Button(
            onClick = onSignUpListener,
            modifier = Modifier.wrapContentSize()
    ) {
        Text("Sign In")
    }
}
