package com.sw.mobile.flickrbrowser.api

import com.sw.mobile.flickrbrowser.model.PhotosResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

fun getLogger(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    return logging
}

private val service: IApi by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogger())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(IApi::class.java)
}

fun getApi() = service

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface IApi {
    @Headers("Content-Type: application/json")
    @GET("/services/rest")
    suspend fun search(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String = "f46b7b3920dc5855bdfdbeacd14b3ebd",
        @Query("privacy_filter") privacyFilter: String = "1",
        @Query("content_type") contentType: String = "1",
        @Query("extras", encoded = true) extras: String = "url_s,+url_o,+url_t,+url_m,+url_l",
        @Query("per_page") perPage: Int= 100,
        @Query("page") page: Int= 0,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int= 1,
        @Query("tags") tags: String = "spacex",
        @Query("text") text: String = ""
    ): PhotosResponse
}


