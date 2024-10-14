package mx.mikeni.home

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
        modifier: Modifier = Modifier,
        onButtonListener: () -> Unit
) {
    Text("Home")
    Button(
            onClick = onButtonListener,
            modifier = Modifier.wrapContentSize()
    ) {
        Text("Sign Up")
    }
}
