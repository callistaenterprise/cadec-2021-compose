package com.sw.mobile.flickrbrowser.composables

import FlickrViewModelMock
import androidx.compose.runtime.Providers
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.sw.mobile.flickrbrowser.components.SearchInputTextContainer
import com.sw.mobile.flickrbrowser.model.AmbientFlickrSearch
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme
import org.junit.Rule
import org.junit.Test

class SearchInputTextContainerKtTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun searchInputTextNotLoading() {
    val flickrViewModel = FlickrViewModelMock(_searchText = "")
    composeTestRule.setContent {
      FlickrbrowserTheme {
        Providers(AmbientFlickrSearch provides flickrViewModel) {
          SearchInputTextContainer()
        }
      }
    }
    composeTestRule.onNodeWithTag("search-input").assertTextEquals("")
    composeTestRule.onNodeWithTag("icon-not-loading", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("search-input").performTextInput("Space")
    composeTestRule.onNodeWithTag("search-input").assertTextEquals("Space")
    composeTestRule.onNodeWithTag("icon-loading", useUnmergedTree = true).assertExists()
    // should revert to not loading

    composeTestRule.onNodeWithTag("icon-not-loading", useUnmergedTree = true).assertDoesNotExist()
    Thread.sleep(2000)
  }
}