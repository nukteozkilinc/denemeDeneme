package com.nukte.denemedeneme.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nukte.denemedeneme.News

@Dao
interface NewsDao {
    @Insert
    suspend fun insert(news: News)

    @Query("SELECT * FROM newsTable order by newsId ASC") //@get:Query
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT EXISTS(SELECT * FROM newsTable WHERE publishedAt == :publishedAt)")
    suspend fun isSavedBefore(publishedAt: String): Boolean

    //@Delete
    //suspend fun deleteNews(news: News)
    @Query("DELETE from newsTable where publishedAt = :publishedAt")
    suspend fun deleteNews(vararg publishedAt: String)
}