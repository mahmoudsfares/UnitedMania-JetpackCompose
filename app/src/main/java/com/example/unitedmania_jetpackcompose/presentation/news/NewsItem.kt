package com.example.unitedmania_jetpackcompose.presentation.news

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewsItem(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Background color
        )
    ) {
        Text(
            title, color = Color.White, modifier = Modifier.padding(all = 16.dp),
        )
    }
}