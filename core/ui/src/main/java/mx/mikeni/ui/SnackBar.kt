package mx.mikeni.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
private fun CustomSnackBar(
        message: String,
        containerColor: Color
) {
    Snackbar(
            modifier = Modifier.padding(Space12),
            containerColor = containerColor
    ) {
        Text(text = message, color = Neutral00)
    }
}

@Composable
fun SuccessSnackBar(message: String) {
    CustomSnackBar(message = message, containerColor = Success)
}

@Composable
fun ErrorSnackBar(message: String) {
    CustomSnackBar(message = message, containerColor = Error)
}
