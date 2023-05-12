package com.ronald.photosearchapp.data.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitConnection {
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }
    private val builder = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/services/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    val service: FlickrService = builder.create()
}