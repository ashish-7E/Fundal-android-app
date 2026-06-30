# MIRA - Maternal Intelligence Remote Analysis

MIRA is a modern Android application designed to facilitate remote maternal health analysis through computer vision. It allows users to upload videos of themselves, provide physical measurements for calibration, and receive detailed 3D reconstruction and analysis results from a remote AI pipeline.

## Key Features

*   **Intelligent Video Upload**: High-performance multipart video upload system with real-time status tracking.
*   **Scale Calibration**: Innovative calibration logic using user-inputted height to ensure accurate 3D measurements.
*   **Live Progress Monitoring**: Asynchronous log streaming and status polling from the backend server during analysis.
*   **Modern UI/UX**: Fully built with Jetpack Compose, featuring a centered, accessible design and a seamless navigation flow.
*   **Integrated Media Support**: Built-in video preview using Media3 ExoPlayer.

## Tech Stack

*   **Language**: Kotlin (2.2.10)
*   **UI Framework**: Jetpack Compose
*   **Dependency Injection**: Dagger Hilt
*   **Networking**: Retrofit & Moshi
*   **Concurrency**: Kotlin Coroutines & Flow
*   **Media**: Media3 ExoPlayer & Coil
*   **Architecture**: MVVM (Model-View-ViewModel) with Clean Architecture principles

## How It Works

1.  **Splash Screen**: Branding and initialization.
2.  **Home**: Start the analysis process.
3.  **Upload**: Select a video from the gallery, enter physical height (cm) for scale calibration, and watch a preview.
4.  **Processing**: The app uploads the video to the FastAPI/Flask backend hosted on Hugging Face Spaces and initiates the analysis pipeline.
5.  **Progress**: A live log viewer displays real-time updates from the server as the AI models (SAM, VGGT) process the data.
6.  **Results**: Once complete, the app displays calculated metrics like Fundal Height and Bulge Volume.

## Setup & Configuration

### Prerequisites
*   Android Studio Ladybug or newer
*   JDK 17+
*   Android Device/Emulator (API 26+)

### Installation
1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Update the `BASE_URL` in `RetrofitModule.kt` to point to your backend server.
4.  Sync Gradle and run the `:app` module.

## icense
This project is licensed under the Apache License 2.0.
