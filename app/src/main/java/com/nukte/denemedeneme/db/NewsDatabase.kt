package com.nukte.denemedeneme.db

import android.content.Context
import androidx.room.*
import com.nukte.denemedeneme.News
import dagger.Provides


@Database(
    entities = [News::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao


    companion object{
        @Volatile
        private var instance : NewsDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance?:createDatabase(context).also{ instance=it}
        }
        private fun createDatabase(context : Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "newsTable"
            ).build()
    }
}