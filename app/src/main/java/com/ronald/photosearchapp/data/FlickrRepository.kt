package com.ronald.photosearchapp.data

import com.ronald.photosearchapp.data.server.RetrofitConnection
import com.ronald.photosearchapp.domain.Photo

class FlickrRepository(private val apiKey:String) {

    suspend fun getFeed(page: Int, photosPerPage: Int): List<Photo> {
        val result = RetrofitConnection.service.getRecentPhotos(apiKey, page, photosPerPage)
        return result.photosInfo.photos.map { it.toDomainPhoto() }
    }

    suspend fun searchPhotosBy(page: Int, photosPerPage: Int, text: String): List<Photo> {
        val result = RetrofitConnection.service.getSearchPhotos(apiKey, text, page, photosPerPage)
        return result.photosInfo.photos.map { it.toDomainPhoto() }
    }
}