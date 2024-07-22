package com.example.demo.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun FloatingButton(click: () -> Unit) {
    FloatingActionButton(onClick = click) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Statistics"
        )
    }
}
