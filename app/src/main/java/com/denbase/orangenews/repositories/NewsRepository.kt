package com.denbase.orangenews.repositories

import com.denbase.orangenews.data.ArticleItem
import com.denbase.orangenews.utils.Resource

interface NewsRepository {

    suspend fun getTopHeadlinesNews(): Resource<ArticleItem>

}