package com.nukte.denemedeneme.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.api.NewsApi
import retrofit2.HttpException
import java.io.IOException

class NewsPaginigSource(
    private val api : NewsApi
) : PagingSource<Int, News>(){
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val pageIndex = params.key?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = api.getNews(
                page = pageIndex
            )
            val news = response.articles
            val nextKey =
                if(news.isEmpty()){
                    null
                }else{
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = news,
                prevKey = if(pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object{
        var TMDB_STARTING_PAGE_INDEX = 1
        var NETWORK_PAGE_SIZE = 50
    }
}