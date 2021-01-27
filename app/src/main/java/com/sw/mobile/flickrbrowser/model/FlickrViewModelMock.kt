import androidx.lifecycle.viewModelScope
import com.sw.mobile.flickrbrowser.api.getApi
import com.sw.mobile.flickrbrowser.model.FlickrViewModel
import com.sw.mobile.flickrbrowser.model.Photo
import com.sw.mobile.flickrbrowser.model.Photos
import com.sw.mobile.flickrbrowser.model.PhotosResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var _photos = listOf(
  Photo(
    id = "1",
    title = "Turksat Launch",
    url_m = "https://live.staticflickr.com/65535/50821899833_8cc49eb217.jpg"
  ),
  Photo(
    id = "2",
    title = "SpaceX Launch with Turksat 5A Satellite 1/7/2021",
    url_m = "https://live.staticflickr.com/65535/50814082791_dc7487222b.jpg"
  ),
  Photo(
    id = "3",
    title = "SpaceX Starts 2021 Right!",
    url_m = "https://live.staticflickr.com/65535/50813000172_77b6287745.jpg"
  ),
  Photo(
    id = "4",
    title = "SpaceX CRS-21 Rollout",
    url_m = "https://live.staticflickr.com/65535/50772694521_99b8522bf1.jpg",
  ),
)
var _photosResponseMocks = PhotosResponse(photos = Photos(photo = _photos))

class FlickrViewModelMock(_searchText: String = "") :
  FlickrViewModel(_searchText, _photos = _photosResponseMocks) {
  override fun onSearchFlickr(value: String) {
    if (value.length < 3) return
    loading = true
    searchJob = viewModelScope.launch {
      delay(1000)
      photos = PhotosResponse(photos = Photos(photo = _photos.filter {
        it.title.contains(
          value,
          ignoreCase = true
        )
      }))
      loading = false
    }
  }
}