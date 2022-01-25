package com.example.pagingroomdbmvvm.data.model


import com.google.gson.annotations.SerializedName


data class New(
    @SerializedName("favorite")
    val favorite: Int = 0,
    @SerializedName("image_source_link")
    val imageSourceLink: String = "",
    @SerializedName("image_source_title")
    val imageSourceTitle: String = "",
    @SerializedName("member_first_name")
    val memberFirstName: String = "",
    @SerializedName("member_id")
    val memberId: String = "",
    @SerializedName("member_last_name")
    val memberLastName: String = "",
    @SerializedName("member_like")
    val memberLike: Int = 0,
    @SerializedName("member_profile_pic")
    val memberProfilePic: String = "",
    @SerializedName("member_username")
    val memberUsername: String = "",
    @SerializedName("news_content")
    val newsContent: String = "",
    @SerializedName("news_id")
    val newsId: String = "",
    @SerializedName("news_image")
    val newsImage: String = "",
    @SerializedName("news_publishdate")
    val newsPublishdate: String = "",
    @SerializedName("news_title")
    val newsTitle: String = "",
    @SerializedName("news_url")
    val newsUrl: String = "",
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("tags")
    val tags: String = "",
    @SerializedName("thumb_image")
    val thumbImage: String = "",
    @SerializedName("total_comment")
    val totalComment: Int = 0,
    @SerializedName("total_like")
    val totalLike: Int = 0,
    @SerializedName("total_views")
    val totalViews: Int = 0
)