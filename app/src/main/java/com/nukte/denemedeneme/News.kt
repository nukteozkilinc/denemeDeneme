package com.nukte.denemedeneme
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "newsTable")
@Serializable
data class News(
    @PrimaryKey(autoGenerate = true)
    var newsId : Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "description")
    var description : String,
    @ColumnInfo(name = "urlToImage")
    var urlToImage : String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt : String,
    @ColumnInfo(name = "content")
    var content : String,
    var isSaved : Boolean
) : java.io.Serializable