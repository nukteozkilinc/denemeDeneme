package com.nukte.denemedeneme.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.nukte.denemedeneme.News
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
     suspend fun getHomeNews() : Flow<PagingData<News>>
     suspend fun searchHomeNews(query : String) : List<News>
}