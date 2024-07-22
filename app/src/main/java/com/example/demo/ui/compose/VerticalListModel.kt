package com.example.demo.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demo.data.model.ItemData

/**
 * Compose view for the list item
 * */
@Composable
fun VerticalListModel(item: ItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Color(0xFFcde8e1), shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.image), // Replace with your image loading logic
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(
                    shape =
                    RoundedCornerShape(10.dp), border = BorderStroke(.5.dp, Color.Gray)
                )
                .size(60.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = item.title , Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.padding(3.dp))
            Text(text = item.description , Modifier.fillMaxWidth())
        }
    }
}
