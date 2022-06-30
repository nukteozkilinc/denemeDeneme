package com.nukte.denemedeneme.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nukte.denemedeneme.News
import com.nukte.denemedeneme.data.NewsDataSource
import com.nukte.denemedeneme.data.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailScreenViewModel @Inject constructor(
    private val newsRepositoryImp: NewsRepository
): ViewModel() {

}