package com.sw.mobile.flickrbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.setContent
import com.sw.mobile.flickrbrowser.composables.Counters
import com.sw.mobile.flickrbrowser.composables.CountersAmbient
import com.sw.mobile.flickrbrowser.composables.CountersContainer
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

class CounterActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FlickrbrowserTheme {
        Column {
          Counters()
          CountersContainer()
          CountersAmbient()
        }
      }
    }
  }
}

