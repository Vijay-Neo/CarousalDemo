package com.example.demo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.demo.ui.compose.Content
import com.example.demo.ui.compose.FloatingButton
import com.example.demo.ui.theme.DemoTheme
import com.example.demo.viewmodel.MainViewModelImpl

/**
 * This is the main activity of the application
 * */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTheme {
                val viewModel by viewModels<MainViewModelImpl>()
                val showBottomSheet =
                    rememberSaveable { mutableStateOf(false) } //State for bottom sheet

                Scaffold(
                    topBar = { },
                    floatingActionButton = {
                        FloatingButton() { showBottomSheet.value = true }
                    }
                ) { innerPadding -> // Use innerPadding to avoid content overlap
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding) // Apply padding from Scaffold
                    ) {
                        Content(showBottomSheet, viewModel)
                    }
                }
            }
        }
    }
}







