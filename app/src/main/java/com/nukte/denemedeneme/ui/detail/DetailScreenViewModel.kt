package com.nukte.denemedeneme.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val newsRepositoryImp: NewsRepository

): ViewModel() {

    fun saveNews(news:News) =viewModelScope.launch {
        news.isSaved=true
        newsRepositoryImp.saveNews(news)

    }

    fun deleteNews(news:News) = viewModelScope.launch {
        news.isSaved = false
        newsRepositoryImp.deleteNews(news._id)

    }

}