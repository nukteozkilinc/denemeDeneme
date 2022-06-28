package com.nukte.denemedeneme.data

import com.nukte.denemedeneme.News

interface NewsDataSource {
     suspend fun getHomeNews() : List<News>
}