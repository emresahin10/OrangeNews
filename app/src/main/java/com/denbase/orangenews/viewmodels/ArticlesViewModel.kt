package com.denbase.orangenews.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.denbase.orangenews.data.ArticleItem
import com.denbase.orangenews.repositories.NewsRepository
import com.denbase.orangenews.utils.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    application: Application,
    private val repository: NewsRepository
): AndroidViewModel(application) {


    private var _articleStatus = MutableLiveData<Resource<ArticleItem>>()
    var articleList: LiveData<Resource<ArticleItem>> = _articleStatus


    fun getTopHeadlinesNews(){
        _articleStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            _articleStatus.postValue(repository.getTopHeadlinesNews())
        }
    }
}