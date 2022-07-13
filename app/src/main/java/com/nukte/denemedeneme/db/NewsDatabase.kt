package com.nukte.denemedeneme.db

import androidx.room.*
import com.nukte.denemedeneme.News


@Database(
    entities = [
        News::class
               ],
    version = 2
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}