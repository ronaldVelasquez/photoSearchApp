package com.ronald.photosearchapp.domain


data class Photo(
    val title: String,
    val publish: String,
    val author: String,
    val url: String? =  ""
)
