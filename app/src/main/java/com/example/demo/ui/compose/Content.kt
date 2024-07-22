package com.example.demo.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.demo.utils.BottomSheetDialog
import com.example.demo.viewmodel.MainViewModelImpl

@Composable
fun Content(showBottomSheet: MutableState<Boolean>, viewModel: MainViewModelImpl = MainViewModelImpl()) {
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