package com.nukte.denemedeneme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.NewsDataSource
import com.nukte.denemedeneme.data.repository.NewsRepository
import com.nukte.denemedeneme.data.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsDataSource: NewsDataSource,
    private val newsRepositoryImp: NewsRepository
) : ViewModel() {
    private val newsLiveData = MutableLiveData<List<News>>()
    val news : LiveData<List<News>> = newsLiveData

    init {
       fetchNews()
    }

    fun fetchNews() =  viewModelScope.launch {
        val news = newsDataSource.getHomeNews()
        delay(2000)
        newsLiveData.value = news
    }
    fun saveNews(news: News) = viewModelScope.launch {
            newsRepositoryImp.saveNews(news)
            news.isSaved=true
    }
    fun deleteNews(news: News) = viewModelScope.launch {
            newsRepositoryImp.deleteNews(news.publishedAt)
            news.isSaved=false


    }
}