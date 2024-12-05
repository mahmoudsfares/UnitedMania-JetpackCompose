package com.example.unitedmania_jetpackcompose.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class News(
    @SerializedName("articles") val articles: List<Article>? = null
)

// TODO 21: serialization - to be able to pass the article as an argument, annotate its data class and all its sub classes with @Serializable
@Serializable
data class ArticleSource(
    @SerializedName("name") val sourceName: String? = null
)

@Serializable
data class Article(
    @SerializedName("source") val source: ArticleSource,
    @SerializedName("title") val title: String,
    @SerializedName("content") val details: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val imageUrl: String
)