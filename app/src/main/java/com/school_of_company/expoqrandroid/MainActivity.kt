package com.school_of_company.expoqrandroid

import ExpoQrApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.school_of_company.expoqrandroid.ui.theme.ExpoQrAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpoQrAndroidTheme {
                ExpoQrApp()
            }
        }
    }
}
