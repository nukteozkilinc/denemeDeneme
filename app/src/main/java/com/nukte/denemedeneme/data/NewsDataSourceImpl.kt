package com.nukte.denemedeneme.data

import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.api.NewsApi
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val api : NewsApi
) : NewsDataSource {
    override suspend fun getHomeNews(): List<News> {
        val newList = api.getNews()
        return newList.articles
    }
}