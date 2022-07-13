package com.nukte.denemedeneme.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.nukte.denemedeneme.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun saveNews(news: News)
    fun getAllNews(): LiveData<List<News>>
    suspend fun deleteNews(_id: String)

}