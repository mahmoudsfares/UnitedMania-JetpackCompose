package com.example.unitedmania_jetpackcompose.presentation.details

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.unitedmania_jetpackcompose.R

// TODO 26: loading image - create a widget for loading the article's image in details screen
@Composable
fun MyAsyncImage(url: String){
    AsyncImage(
        model = url,
        contentDescription = null,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.placeholder),
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.25f)
    )
}