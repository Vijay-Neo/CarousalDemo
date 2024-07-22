package com.example.demo.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.demo.viewmodel.MainViewModelImpl


/**
 * to display Statistics Content on bottomsheet page
 */
@Composable
fun StatisticsContent(viewModel: MainViewModelImpl) {
    val pageItems by viewModel.filteredListData.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        if(pageItems.isNotEmpty()) {
            Text("${pageItems.size} items")
            val top3Chars = viewModel.getStatistics()
            top3Chars.forEach {
                Text("${it.key} = ${it.value}")
            }
            Spacer(modifier = Modifier.height(16.dp)) // Add spacing between pages
        }else {
            Text("No Data", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(20.dp))
        }
    }
}