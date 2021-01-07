package com.sw.mobile.flickrbrowser.model

import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.mobile.flickrbrowser.api.getApi
import kotlinx.coroutines.launch

class FlickrViewModel : ViewModel() {
    private var _items = listOf(
        FlickrItem(
            id = "1",
            title = "The Moon",
            date = "2018-12-12 14:05:33",
            imageUrl = ""
        ),
        FlickrItem(
            id = "2",
            title = "fred",
            date = "2018-12-12 14:05:33",
            imageUrl = ""
        ),
        FlickrItem(
            id = "3",
            title = "barney",
            date = "2018-12-12 14:05:33",
            imageUrl = ""
        ),
        FlickrItem(
            id = "4",
            title = "steve",
            date = "2018-12-12 14:05:33",
            imageUrl = ""
        ),
        FlickrItem(
            id = "5",
            title = "alfred",
            date = "2018-12-12 14:05:33",
            imageUrl = ""
        )
    )
    var photos by mutableStateOf(PhotosResponse())
    var searchText by mutableStateOf("")
        private set
    var loading by mutableStateOf(false)
    var items by mutableStateOf(listOf<FlickrItem>())

    init {
        viewModelScope.launch {
            // This coroutine will be canceled when the ViewModel is cleared.
            items = _items
        }
    }


    fun onSearchText(value: String) {
        searchText = value
        items = _items.filter { it.title.contains(value, ignoreCase = true) }
    }

    fun onSearchFlickr(value: String) {
        loading = true
        viewModelScope.launch {
            photos = getApi().search(tags = "spacex", text = value)
        }
        loading = false
    }

}


val AmbientFlickrSearch = ambientOf<FlickrViewModel>()
