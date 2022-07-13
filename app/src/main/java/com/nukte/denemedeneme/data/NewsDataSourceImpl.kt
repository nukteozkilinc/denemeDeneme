package com.nukte.denemedeneme.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.api.NewsApi
import com.nukte.denemedeneme.data.pagging.NewsPaginigSource
import com.nukte.denemedeneme.data.pagging.NewsPaginigSource.Companion.NETWORK_PAGE_SIZE
import com.nukte.denemedeneme.db.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val api: NewsApi
) : NewsDataSource {
        override suspend fun getHomeNews(): Flow<PagingData<News>> {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    NewsPaginigSource(api = api)
                }
            ).flow
    }

    override suspend fun searchHomeNews(query :String): List<News> {
        val searchList = api.searchNews(query = query).articles
        return searchList

    }

    companion object{
       const val NETWORK_PAGE_SIZE = 50
    }
}
