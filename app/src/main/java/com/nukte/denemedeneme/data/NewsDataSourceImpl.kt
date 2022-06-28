package com.nukte.denemedeneme.data

import androidx.lifecycle.map
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.api.NewsApi
import com.nukte.denemedeneme.db.NewsDao
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val api: NewsApi,
    private val newsDao: NewsDao
) : NewsDataSource {
    override suspend fun getHomeNews(): List<News> {
        val newsList = api.getNews().articles
        newsList.map {
            newsDao.isSavedBefore(publishedAt = it.publishedAt).let { isSavedBefore ->
                if (isSavedBefore) it.isSaved = true
            }
        }
        return newsList
    }
}