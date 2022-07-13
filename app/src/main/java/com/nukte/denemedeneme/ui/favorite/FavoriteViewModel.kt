package com.nukte.denemedeneme.ui.favorite

import androidx.lifecycle.*
import androidx.paging.*
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.api.NewsApi
import com.nukte.denemedeneme.data.repository.NewsRepositoryImpl
import com.nukte.denemedeneme.db.NewsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val newsRepositoryImp: NewsRepositoryImpl
) : ViewModel() {

    private val newsLiveData = MutableLiveData<List<News>>()
    var news: LiveData<List<News>> = newsLiveData

    fun getSavedNews() = newsRepositoryImp.getAllNews()


    fun deleteNews(news: News) = viewModelScope.launch {
        newsRepositoryImp.deleteNews(news._id)
        news.isSaved=false
    }



}