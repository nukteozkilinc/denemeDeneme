package com.nukte.denemedeneme.di

import com.nukte.denemedeneme.data.NewsDataSource
import com.nukte.denemedeneme.data.NewsDataSourceImpl
import com.nukte.denemedeneme.data.repository.NewsRepository
import com.nukte.denemedeneme.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsDataSource(newsDataSourceImpl: NewsDataSourceImpl): NewsDataSource

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository
}