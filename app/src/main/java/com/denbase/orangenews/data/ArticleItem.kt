package com.denbase.orangenews.data

data class ArticleItem(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)