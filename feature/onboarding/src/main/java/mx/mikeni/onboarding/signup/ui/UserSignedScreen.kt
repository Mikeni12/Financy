package mx.mikeni.onboarding.signup.ui

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun UserSignedScreen(
        onHomeListener: () -> Unit
) {
    Text(
            text = "Registration finished successfully!",
            style = MaterialTheme.typography.titleLarge,
    )
    AsyncImage(
            model = "https://cdn-icons-png.freepik.com/512/9746/9746220.png",
            contentDescription = null,
    )
    Button(
            onClick = onHomeListener,
            modifier = Modifier.wrapContentSize()
    ) {
        Text("Take me Home!")
    }
}
