package com.nukte.denemedeneme.di

import com.nukte.denemedeneme.api.NewsApi
import com.nukte.denemedeneme.data.NewsDataSource
import com.nukte.denemedeneme.data.NewsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLogging : HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(httpLogging).build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttp : OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .client(okHttp)
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit) : NewsApi =
        retrofit.create(NewsApi::class.java)


}