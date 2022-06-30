package com.nukte.denemedeneme.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val newsRepositoryImp: NewsRepository
) : ViewModel() {
    private val newsLiveData = MutableLiveData<List<News>>()
    var news: LiveData<List<News>> = newsLiveData

    fun getSavedNews() = newsRepositoryImp.getAllNews()

    fun deleteNews(news: News) = viewModelScope.launch {
        newsRepositoryImp.deleteNews(news.publishedAt)
        news.isSaved=false
    }


}