package com.ronald.photosearchapp.data.server

import com.ronald.photosearchapp.data.FlickrResult
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET("rest?method=flickr.photos.getRecent&format=json&nojsoncallback=1&extras=last_update,owner_name,date_upload,url_c")
    suspend fun getRecentPhotos(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): FlickrResult

    @GET("rest?method=flickr.photos.search&format=json&nojsoncallback=1&extras=last_update,owner_name,date_upload,url_c")
    suspend fun getSearchPhotos(
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): FlickrResult
}