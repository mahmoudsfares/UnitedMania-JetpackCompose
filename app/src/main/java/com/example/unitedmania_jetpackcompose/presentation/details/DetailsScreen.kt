package com.example.unitedmania_jetpackcompose.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitedmania_jetpackcompose.data.Article
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navHostController: NavHostController, article: Article){
    val context = LocalContext.current

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Use a back arrow icon
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                ),
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
        ){
            MyAsyncImage(article.imageUrl)
            Text(modifier = Modifier.padding(all = 16.dp), text = article.title, color = Color.White, fontSize = 22.sp)
            Text(modifier = Modifier.padding(all = 16.dp), text = formatNewsDetails(article.details), color = Color.White, fontSize = 14.sp)
            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp).align(Alignment.End).clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                context.startActivity(intent)
            }, text = "FULL ARTICLE", color = Color(0xFF89ECDA), fontSize = 20.sp)
        }
    }
}

private fun formatNewsDetails(newsDetails: String): String {
    val detailsStopPosition = newsDetails.indexOf("[")
    return if (detailsStopPosition != -1) {
        newsDetails.substring(0, detailsStopPosition)
    } else {
        newsDetails
    }
}