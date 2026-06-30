package com.example.fundalapp.utils

import android.content.Context
import android.net.Uri
import java.io.File

fun uriToFile(context: Context, uri: Uri): File {
    val inputStream = context.contentResolver.openInputStream(uri)!!
    val file = File.createTempFile("upload_", ".mp4", context.cacheDir)

    file.outputStream().use { output ->
        inputStream.copyTo(output)
    }

    return file
}
