package com.nukte.denemedeneme.ui.dashboard

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.NewsDataSource
import com.nukte.denemedeneme.db.NewsDao
import com.nukte.denemedeneme.db.NewsDatabase
import com.nukte.denemedeneme.db.NewsRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.internal.aggregatedroot.codegen._com_nukte_denemedeneme_NewsApplication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val newsRepositoryImp: NewsRepositoryImp
) : ViewModel() {
    private val newsLiveData = MutableLiveData<List<News>>()
    var news : LiveData<List<News>> = newsLiveData

    fun saveNews(news : News) = viewModelScope.launch {
        newsRepositoryImp.insert(news)
    }

    fun getSavedNews() = newsRepositoryImp.getSavedNews()

    fun deleteNews(news:News) = viewModelScope.launch {
        newsRepositoryImp.deleteNews(news)
    }


}