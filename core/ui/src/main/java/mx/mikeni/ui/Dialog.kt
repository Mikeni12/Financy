package mx.mikeni.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog

import coil.compose.AsyncImage

@Composable
fun DialogWithImage(
        imageUrl: String,
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(Space384)
                        .padding(Space16),
                shape = RoundedCornerShape(Space16),
        ) {
            Column(
                    modifier = Modifier
                            .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                                .height(Space128)
                )
                Text(
                        text = "Verify your identification photo",
                        modifier = Modifier.padding(Space16),
                )
                Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier.padding(Space8),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                            onClick = { onConfirmation() },
                            modifier = Modifier.padding(Space8),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}
