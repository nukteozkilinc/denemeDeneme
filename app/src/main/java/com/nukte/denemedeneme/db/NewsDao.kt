package com.nukte.denemedeneme.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.nukte.denemedeneme.News

@Dao
interface NewsDao {
    @Insert
    suspend fun insert(news: News)

    @Query("SELECT * FROM newsTable ") //@get:Query
    fun getAllNews(): LiveData<List<News>>//burdaki ınt page number

    @Query("SELECT EXISTS(SELECT * FROM newsTable WHERE id == :id)")
    suspend fun isSavedBefore(id: String): Boolean

    //@Delete
    //suspend fun deleteNews(news: News)
    @Query("DELETE from newsTable where id ==:id")
    suspend fun deleteNews(id : String)

}