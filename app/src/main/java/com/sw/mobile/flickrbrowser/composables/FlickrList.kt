package com.sw.mobile.flickrbrowser.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sw.mobile.flickrbrowser.model.AmbientFlickrSearch
import com.sw.mobile.flickrbrowser.model.FlickrItem
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

@Composable
fun Flickrlist(items: List<FlickrItem>? = null) {
    val flickrViewModel = AmbientFlickrSearch.current
    val flickrItems = items ?: flickrViewModel.items
    println("---- FLICKR items " + items)
    println("---- FLICKR LIST items " + flickrViewModel.items)
    if (flickrItems != null) LazyColumnFor(
        items = flickrItems,
        contentPadding = PaddingValues(top = 8.dp)
    ) { item ->
        FlickrRow(flickrItem = item)
    } else {
        Text(
            "Empty List",
        )
    }
}

@Preview
@Composable
fun PreviewFlickrlist() =
    FlickrbrowserTheme() {
        Flickrlist(
            items = listOf(
                FlickrItem(
                    id = "1",
                    title = "The Moon",
                    date = "2018-12-12 14:05:33",
                    imageUrl = ""
                ),
                FlickrItem(
                    id = "2",
                    title = "The Moon 2",
                    date = "2018-12-12 14:05:33",
                    imageUrl = ""
                ),
                FlickrItem(
                    id = "3",
                    title = "The Moon 3",
                    date = "2018-12-12 14:05:33",
                    imageUrl = ""
                ),
                FlickrItem(
                    id = "4",
                    title = "The Moon 4",
                    date = "2018-12-12 14:05:33",
                    imageUrl = ""
                ),
                FlickrItem(
                    id = "5",
                    title = "The Moon 5",
                    date = "2018-12-12 14:05:33",
                    imageUrl = ""
                )

            )
        )
    }

@Preview
@Composable
fun PreviewFlickrlistEmpty() =
    Flickrlist(items = null)
