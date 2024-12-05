package com.example.unitedmania_jetpackcompose.presentation.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.example.unitedmania_jetpackcompose.data.Article
import com.example.unitedmania_jetpackcompose.navigation.Navigation

// TODO 13: di - inject the view model using hiltViewModel()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navHostController: NavHostController, viewModel: NewsViewModel = hiltViewModel()){

    val lazyPagingItems = viewModel.news.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All News", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // expected scenario: a list of items in the page
                items(
                    count = lazyPagingItems.itemCount,
                    contentType = lazyPagingItems.itemContentType { "News" },
                ) { index ->
                    val article: Article? = lazyPagingItems[index]
                    if (article != null) {
                        NewsItem(title = article.title, onClick = {
                            navHostController.navigate(Navigation.DetailsScreen.getRouteWithNonNullableArguments(article))
                        })
                    }
                }
                // loading a new page
                if (lazyPagingItems.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                }
                // error getting a new page
                else if (lazyPagingItems.loadState.append is LoadState.Error) {
                    item {
                        Text(
                            text = "Error loading more items",
                            color = Color.Red
                        )
                    }
                }
            }
            // loading for the first time
            if (lazyPagingItems.loadState.refresh is LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            // error while fetching data for the first time
            else if (lazyPagingItems.loadState.refresh is LoadState.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error loading data",
                        color = Color.White
                    )
                }
            }
        }
    }
}