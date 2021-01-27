package com.sw.mobile.flickrbrowser.composables

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.sw.mobile.flickrbrowser.components.SearchInputText
import org.junit.Rule
import org.junit.Test

class SearchInputTextKtTest {

  @get:Rule
  val composeTestRule = createComposeRule()
  @Test
  fun searchInputTextNotLoading() {
    composeTestRule.setContent {
      SearchInputText(loading=false, onSearchFlickr = {}, searchText = "not loading")
    }
    composeTestRule.onRoot().printToLog("TAG")
    composeTestRule.onNodeWithTag("search-input").assertTextEquals("not loading")
    // does not merge the semantic information of the search inputs children, which would be the icon..
    composeTestRule.onNodeWithTag("icon-not-loading", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("icon-loading", useUnmergedTree = true).assertDoesNotExist()
  }
  @Test
  fun searchInputTextLoading() {
    composeTestRule.setContent {
      SearchInputText(loading=true, onSearchFlickr = {}, searchText = "loading")
    }
    composeTestRule.onNodeWithTag("search-input").assertTextEquals("loading")
    composeTestRule.onNodeWithTag("icon-not-loading", useUnmergedTree = true).assertDoesNotExist()
    composeTestRule.onNodeWithTag("icon-loading", useUnmergedTree = true).assertExists()
  }
}