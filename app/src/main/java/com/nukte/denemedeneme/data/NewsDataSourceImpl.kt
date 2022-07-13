package com.nukte.denemedeneme.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.api.NewsApi
import com.nukte.denemedeneme.data.pagging.NewsPagingSource
import com.nukte.denemedeneme.db.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val api: NewsApi,
    private val newsDao: NewsDao
) : NewsDataSource {
        override suspend fun getHomeNews(): Flow<PagingData<News>> {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    NewsPagingSource(api = api, newsDao = newsDao)
                }
            ).flow
    }

    override suspend fun searchHomeNews(query :String): List<News> {
        val searchList = api.searchNews(query = query).articles
        searchList.map {
            newsDao.isSavedBefore(id = it._id).let { isSavedBefore ->
                when (isSavedBefore) {
                    true -> it.isSaved = true
                    else -> it.isSaved = false
                }
            }
        }
        return searchList

    }

    companion object{
       const val NETWORK_PAGE_SIZE = 50
    }
}
