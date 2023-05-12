package com.ronald.photosearchapp.data

import com.ronald.photosearchapp.common.getDateTime
import com.ronald.photosearchapp.domain.Photo

data class PhotoResult(
    val dateupload: String,
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val lastupdate: String,
    val owner: String,
    val ownername: String,
    val secret: String,
    val server: String,
    val title: String,
    val url_c: String? = ""
)

fun PhotoResult.toDomainPhoto(): Photo= Photo(title, dateupload.getDateTime(), ownername, url_c)