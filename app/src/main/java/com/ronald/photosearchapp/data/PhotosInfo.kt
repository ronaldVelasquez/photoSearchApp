package com.ronald.photosearchapp.data

import com.google.gson.annotations.SerializedName

data class PhotosInfo(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    @SerializedName("photo")
    val photos: List<PhotoResult>,
    val total: Int
)