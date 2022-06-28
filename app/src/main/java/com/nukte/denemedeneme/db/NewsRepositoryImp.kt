package com.nukte.denemedeneme.db
import com.nukte.denemedeneme.News
import javax.inject.Inject


class NewsRepositoryImp @Inject constructor(
    private val db : NewsDatabase){

    fun insert(news:News) = db.newsDao().insertAll(news)

    fun getSavedNews() = db.newsDao().getAllNews()

    fun deleteNews(news:News) = db.newsDao().deleteNews(news.newsId)

}