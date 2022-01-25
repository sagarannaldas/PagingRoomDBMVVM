package com.example.pagingroomdbmvvm.data.model


import com.google.gson.annotations.SerializedName



data class News(
    @SerializedName("news")
    val news: List<New> = listOf(),
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("total_pages")
    val totalPages: Int = 0
)