package com.denbase.orangenews.repositories

import com.denbase.orangenews.api.NewsApi
import com.denbase.orangenews.data.ArticleItem
import com.denbase.orangenews.utils.Resource

class MainNewsRepository(private val api: NewsApi): NewsRepository {

    override suspend fun getTopHeadlinesNews(): Resource<ArticleItem> {
        try {
            val response = api.getTopHeadlinesNews()
            if (response.isSuccessful){
                val body = response.body()
                return Resource.Success(body)
            }
            return Resource.Error(response.message())
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
    }


}