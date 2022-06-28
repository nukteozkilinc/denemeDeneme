package com.nukte.denemedeneme.api

import com.nukte.denemedeneme.NewsResponse
import retrofit2.http.GET

interface NewsApi {

    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines?country=tr&apiKey=dc2e3e80467748aba70f9420250da9f6")
    suspend fun getNews() : NewsResponse
}