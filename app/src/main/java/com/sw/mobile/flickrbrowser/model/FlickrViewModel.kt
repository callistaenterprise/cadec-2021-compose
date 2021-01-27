package com.sw.mobile.flickrbrowser.model

import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.mobile.flickrbrowser.api.getApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class FlickrViewModel(
  val _searchText: String = "",
  val _loading: Boolean = false,
  _photos: PhotosResponse = PhotosResponse()
) : ViewModel() {
  var photos by mutableStateOf(_photos)
  var searchText by mutableStateOf(_searchText)
    private set
  var loading by mutableStateOf(_loading)
  var searchJob: Job? = null

  open fun onSearchFlickr(value: String) {
    if (value.length < 3) return
    searchText = value
    loading = true
    searchJob?.cancel()
    searchJob = viewModelScope.launch {
      delay(500)
      photos = getApi().search(tags = "spacex", text = value)
      loading = false
    }
  }
}

val AmbientFlickrSearch = ambientOf<FlickrViewModel>()
