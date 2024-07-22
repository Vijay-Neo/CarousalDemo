package com.example.demo.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.demo.utils.BottomSheetDialog
import com.example.demo.viewmodel.MainViewModel

@Composable
fun Content(showBottomSheet: MutableState<Boolean>, viewModel: MainViewModel = MainViewModel()) {
    MainScreen(viewModel)
    // Bottom Sheet Dialog
    if (showBottomSheet.value) {
        BottomSheetDialog(
            onDismissRequest = { showBottomSheet.value = false }
        ) {
            StatisticsContent(viewModel)
        }
    }
}