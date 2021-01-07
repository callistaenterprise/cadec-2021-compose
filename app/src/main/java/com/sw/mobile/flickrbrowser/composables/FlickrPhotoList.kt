package com.sw.mobile.flickrbrowser.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sw.mobile.flickrbrowser.model.AmbientFlickrSearch
import com.sw.mobile.flickrbrowser.model.Photo

@Composable
fun FlickrPhotoList(navController: NavHostController, photos: List<Photo>? = null) {
    val flickrViewModel = AmbientFlickrSearch.current
    val flickrItems = photos?: flickrViewModel.photos?.photos?.photo
    println("---- FLICKR photos" + flickrItems)
    if (flickrItems != null) LazyColumnFor(
        items = flickrItems,
        contentPadding = PaddingValues(top = 8.dp)
    ) { photo->
        FlickrPhotoRow(navController = navController, photo = photo )
    } else {
        Text(
            "Empty List",
        )
    }
}

