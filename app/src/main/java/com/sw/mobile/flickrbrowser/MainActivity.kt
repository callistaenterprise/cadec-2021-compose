package com.sw.mobile.flickrbrowser

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.onActive
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sw.mobile.flickrbrowser.composables.FlickrPhotoDetails
import com.sw.mobile.flickrbrowser.composables.LayoutFlickr
import com.sw.mobile.flickrbrowser.model.AmbientFlickrSearch
import com.sw.mobile.flickrbrowser.model.FlickrItem
import com.sw.mobile.flickrbrowser.model.FlickrViewModel
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

class MainActivity : AppCompatActivity() {
    val flickrViewModel by viewModels<FlickrViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrbrowserTheme {
                Providers(AmbientFlickrSearch provides flickrViewModel) {
                    LayoutFlickr(
                    )
                }
                onActive(callback = { flickrViewModel.onSearchFlickr("rocket") })
            }
        }
    }
}

