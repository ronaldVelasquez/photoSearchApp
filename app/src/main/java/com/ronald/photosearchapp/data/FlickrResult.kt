package com.ronald.photosearchapp.data

import com.google.gson.annotations.SerializedName

data class FlickrResult(
    @SerializedName("photos")
    val photosInfo: PhotosInfo,
    val stat: String
)