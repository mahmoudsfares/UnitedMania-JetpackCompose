package com.example.unitedmania_jetpackcompose.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unitedmania_jetpackcompose.data.Article
import com.example.unitedmania_jetpackcompose.data_source.RetrofitInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 5
private const val MAX_SIZE = 15

// TODO 14: di - annotate the view model as a hilt view model
@HiltViewModel
// TODO 16: inject retrofit interface
class NewsViewModel @Inject constructor(private val retrofitInterface: RetrofitInterface) : ViewModel() {

    val news: Flow<PagingData<Article>> = getPagedItems().cachedIn(viewModelScope)

    private fun getPagedItems(): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(PAGE_SIZE, MAX_SIZE, false),
            pagingSourceFactory = { NewsRepo(retrofitInterface) }
        )
        return pager.flow
    }
}