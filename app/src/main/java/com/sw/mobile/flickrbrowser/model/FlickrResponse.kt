package com.sw.mobile.flickrbrowser.model

data class PhotosResponse(val photos: Photos = Photos())
data class Photos(
    val page: String = "",
    val pages: Int = 0,
    val perpage: Int = 0,
    val total: String = "",
    val photo: List<Photo> = listOf<Photo>(),
    val stat: String = ""
)

data class Photo(
    val id: String = "",
    val owner: String = "",
    val secret: String = "",
    val server: String = "",
    val farm: Int = 0,
    val title: String = "",
    val ispublic: Int = 0,
    val isfriend: Int = 0,
    val isfamily: Int = 0,
    val url_s: String = "",
    val height_s: Int = 0,
    val width_s: Int = 0,
    val url_o: String = "",
    val height_o: Int = 0,
    val width_o: Int = 0,
    val url_t: String = "",
    val height_t: Int = 0,
    val width_t: Int = 0,
    val url_m: String = "",
    val height_m: Int = 0,
    val width_m: Int = 0,
    val url_l: String = "",
    val height_l: Int = 0,
    val width_l: Int = 0
)
