package com.nukte.denemedeneme.data.repository

import androidx.lifecycle.LiveData
import com.nukte.denemedeneme.News

interface NewsRepository {
    suspend fun saveNews(news: News)
    fun getAllNews(): LiveData<List<News>>
    suspend fun deleteNews(publishedAt: String)
}