package com.example.fundalapp.screens.quality

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun QualityScreen(navController: NavController, jobId: String) {

    var status by remember { mutableStateOf("Checking video quality...") }

    LaunchedEffect(Unit) {

        delay(1500)

        status = "Video validated for job: $jobId"

        delay(1000)

        navController.navigate("progress/$jobId")
    }

    Text(status)
}
