package mx.mikeni.ui

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun Context.createTempPictureUri(): Uri {
    val tempFile = File.createTempFile("picture_${System.currentTimeMillis()}", DEFAULT_IMAGE_SUFFIX, cacheDir).apply {
        createNewFile()
        deleteOnExit()
    }
    return FileProvider.getUriForFile(applicationContext, DEFAULT_IMAGE_AUTHORITY, tempFile)
}

private const val DEFAULT_IMAGE_SUFFIX = ".png"
private const val DEFAULT_IMAGE_AUTHORITY = "mx.mikeni.financy.provider"
