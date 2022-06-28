package com.nukte.denemedeneme.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nukte.denemedeneme.News

@Dao
interface NewsDao {
    @Insert
    fun insertAll( news:News)

    @Query("SELECT * FROM newsTable order by newsId ASC") //@get:Query
    fun getAllNews() : LiveData<List<News>>

    //@Delete
    //suspend fun deleteNews(news: News)
    @Query("DELETE from newsTable where newsId = :newsId")
    fun deleteNews(vararg newsId  : Int) : Int
}