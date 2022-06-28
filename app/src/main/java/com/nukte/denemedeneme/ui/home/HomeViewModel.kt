package com.nukte.denemedeneme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.NewsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsDataSource: NewsDataSource
) : ViewModel() {
    private val newsLiveData = MutableLiveData<List<News>>()
    val news : LiveData<List<News>> = newsLiveData

    init {
        viewModelScope.launch {
            val news = newsDataSource.getHomeNews()
            delay(2000)
            newsLiveData.value = news
        }
    }
}