package com.example.fundalapp.network

import com.example.fundalapp.models.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

data class StartJobRequest(
    val pipeline_kind: String = "fundal",
    val subject: String,
    val input_path: String,
    val output_dir: String,
    val scale_path: String = "",
    val seed_path: String = "",
    val use_seed_picker: String = "no_seed",
    val n_frames: Int = 30,
    val recon_max_frames: Int = 20,
    val recon_model: String = "vggt",
    val pose_model: String = "human",
    val sam_confidence: Double = 0.25,
    val seed_usage: String = "default",
    val poisson_depth: Int = 8,
    val conf_pct_keep: Int = 75,
    val sam_fallback_prompts: String = "",
    val height_cm: Double? = null
)

data class StartJobResponse(
    val ok: Boolean,
    val job_id: String
)

data class SummaryCard(
    val label: String,
    val value: String
)

data class JobStatusResponse(
    val job_id: String,
    val status: String,
    val logs: List<String>,
    val summary_cards: List<SummaryCard>? = null
)

interface ApiService {

    @Multipart
    @POST("/api/upload")
    suspend fun uploadVideo(
        @Part file: MultipartBody.Part
    ): UploadResponse

    @POST("/api/jobs/start")
    suspend fun startJob(
        @Body request: StartJobRequest
    ): StartJobResponse

    @GET("/api/jobs/{jobId}")
    suspend fun getJobStatus(
        @Path("jobId") jobId: String
    ): JobStatusResponse
}
