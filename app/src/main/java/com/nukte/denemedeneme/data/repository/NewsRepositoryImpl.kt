package com.nukte.denemedeneme.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.db.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
): NewsRepository{
    override suspend fun saveNews(news: News) = newsDao.insert(news)
    override fun getAllNews(): LiveData<List<News>> = newsDao.getAllNews()
    override suspend fun deleteNews(_id: String) {
        newsDao.deleteNews(_id)
    }

}