package com.example.fundalapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.fundalapp.screens.home.HomeScreen
import com.example.fundalapp.screens.upload.UploadScreen
import com.example.fundalapp.screens.splash.SplashScreen
import com.example.fundalapp.screens.quality.QualityScreen
import com.example.fundalapp.screens.progress.ProgressScreen
import com.example.fundalapp.screens.results.ResultsScreen
import com.example.fundalapp.screens.details.DetailsScreen
import com.example.fundalapp.screens.report.ReportScreen
import com.example.fundalapp.viewmodel.UploadViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route,
    ) {

        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }

        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        // Shared ViewModel Scope
        navigation(
            startDestination = Routes.Upload.route,
            route = "upload_flow"
        ) {
            composable(Routes.Upload.route) { backStackEntry ->
                val viewModel: UploadViewModel = hiltViewModel(remember(backStackEntry) {
                    navController.getBackStackEntry("upload_flow")
                })
                UploadScreen(navController, viewModel)
            }

            composable(
                route = Routes.Quality.route,
                arguments = listOf(navArgument("jobId") { type = NavType.StringType })
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId")!!
                QualityScreen(navController, jobId)
            }

            composable(
                route = Routes.Progress.route,
                arguments = listOf(navArgument("jobId") { type = NavType.StringType })
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId")!!
                val viewModel: UploadViewModel = hiltViewModel(remember(backStackEntry) {
                    navController.getBackStackEntry("upload_flow")
                })
                ProgressScreen(navController, jobId, viewModel)
            }
            
            composable(
                route = Routes.Results.route,
                arguments = listOf(navArgument("jobId") { type = NavType.StringType })
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId")!!
                val viewModel: UploadViewModel = hiltViewModel(remember(backStackEntry) {
                    navController.getBackStackEntry("upload_flow")
                })
                ResultsScreen(navController, jobId, viewModel)
            }
        }

        composable(Routes.Details.route) {
            DetailsScreen(navController)
        }

        composable(Routes.Report.route) {
            ReportScreen(navController)
        }
    }
}
