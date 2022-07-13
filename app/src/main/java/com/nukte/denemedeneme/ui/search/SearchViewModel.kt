package com.nukte.denemedeneme.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.NewsDataSource
import com.nukte.denemedeneme.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsDataSource: NewsDataSource,
    private val newsRepositoryImp: NewsRepository
) : ViewModel() {
    private val newsLiveData = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = newsLiveData

    fun getSearchNews(query : String) = viewModelScope.launch {
        val news = newsDataSource.searchHomeNews(query)
        newsLiveData.value=news
    }
    fun saveNews(news: News) = viewModelScope.launch {
        news.isSaved = true
        newsRepositoryImp.saveNews(news)
    }

    fun deleteNews(news: News) = viewModelScope.launch {
        news.isSaved = false
        newsRepositoryImp.deleteNews(news._id)
    }
}