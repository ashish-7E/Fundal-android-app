package com.example.fundalapp.models

data class AnalysisState(
    val videoUri: String,
    val jobId: String? = null,
    val status: String = "uploaded"
)
