package com.example.sebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sebook.ui.theme.SebookTheme
import com.example.sebook.ui.theme.screens.AppRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SebookTheme {
                AppRoot()
            }
        }
    }
}
