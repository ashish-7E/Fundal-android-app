package com.example.fundalapp.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundalapp.network.ApiService
import com.example.fundalapp.network.StartJobRequest
import com.example.fundalapp.network.SummaryCard
import com.example.fundalapp.utils.uriToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    var jobId by mutableStateOf<String?>(null)
    var logs by mutableStateOf<List<String>>(emptyList())
    var status by mutableStateOf("idle")
    var subjectHeight by mutableStateOf("")
    var summaryCards by mutableStateOf<List<SummaryCard>>(emptyList())

    fun uploadVideo(context: Context, uri: Uri) {
        viewModelScope.launch {
            try {
                status = "uploading"
                val file = uriToFile(context, uri)

                val requestFile = file.asRequestBody("video/mp4".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestFile
                )

                val response = api.uploadVideo(body)

                startPipeline(response.input_path)

            } catch (e: Exception) {
                status = "upload_failed"
                logs = listOf(e.message ?: "error")
            }
        }
    }

    private fun startPipeline(inputPath: String) {
        viewModelScope.launch {
            try {
                status = "starting"
                val job = api.startJob(
                    StartJobRequest(
                        subject = "pregnant",
                        input_path = inputPath,
                        output_dir = inputPath.replace("data/input", "data/output"),
                        recon_max_frames = 6,
                        height_cm = subjectHeight.toDoubleOrNull()
                    )
                )

                jobId = job.job_id
                status = "running"
                pollJob(job.job_id)
            } catch (e: Exception) {
                status = "failed"
                logs = listOf("Pipeline Error: ${e.message}")
            }
        }
    }

    private suspend fun pollJob(jobId: String) {
        while (true) {
            delay(2000)

            val res = api.getJobStatus(jobId)

            logs = res.logs
            summaryCards = res.summary_cards ?: emptyList()

            if (res.status == "completed" || res.status == "failed") {
                status = res.status
                break
            }
        }
    }
}
