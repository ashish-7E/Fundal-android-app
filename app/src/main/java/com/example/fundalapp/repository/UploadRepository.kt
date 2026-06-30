package com.example.fundalapp.repository

import com.example.fundalapp.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun uploadVideo(file: File): String {
        val requestFile = file.asRequestBody("video/mp4".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        
        return try {
            val response = apiService.uploadVideo(body)
            if (response.ok) {
                response.input_path
            } else {
                "error_upload_failed"
            }
        } catch (e: Exception) {
            "error_${e.message}"
        }
    }
}
