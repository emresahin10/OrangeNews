package com.denbase.orangenews.api

import com.denbase.orangenews.data.ArticleItem
import com.denbase.orangenews.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlinesNews(
        @Query("country") country: String = "us",
        @Query("apiKey") api: String = API_KEY
    ): Response<ArticleItem>
}