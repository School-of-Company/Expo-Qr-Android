package com.school_of_company.expoqrandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.school_of_company.expoqrandroid.qr.view.QrScannerScreen


@Composable
fun ExpoQrApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "app"
    ) {
        navigation(
            route = "app",
            startDestination = "qrScreen"
        ) {
            composable("qrScreen") {
                QrScannerScreen()
            }
        }
    }
}