package mx.mikeni.onboarding.signup.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import mx.mikeni.ui.Space384
import mx.mikeni.ui.Space64
import mx.mikeni.ui.createTempPictureUri

@Composable
fun ColumnScope.UserPhotoScreen(
        photoUri: Uri,
        onTakePhotoListener: (Uri) -> Unit
) {
    val context = LocalContext.current
    var tempPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                if (success) onTakePhotoListener(tempPhotoUri)
            }
    )
    Text(
            text = "Take a photo to finish your registration",
            style = MaterialTheme.typography.titleLarge,
    )
    Button(
            onClick = {
                tempPhotoUri = context.createTempPictureUri()
                cameraLauncher.launch(tempPhotoUri)
            },
            modifier = Modifier
                    .padding(bottom = Space64)
    ) {
        Text("Take Photo")
    }
    AnimatedVisibility(photoUri.toString().isNotBlank()) {
        AsyncImage(
                model = photoUri,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(Space384)
        )
    }
}
