package com.example.fundalapp.navigation

sealed class Routes(val route: String) {

    data object Splash : Routes("splash")

    data object Home : Routes("home")

    data object Upload : Routes("upload")

    data object Quality : Routes("quality/{jobId}")

    data object Progress : Routes("progress/{jobId}")

    data object Results : Routes("results/{jobId}")

    data object Details : Routes("details")

    data object Report : Routes("report")
}
