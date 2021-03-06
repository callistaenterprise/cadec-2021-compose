package com.sw.mobile.flickrbrowser

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.sw.mobile.flickrbrowser.composables.CounterWithState
import com.sw.mobile.flickrbrowser.composables.Counters
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CounterKtTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<CounterActivity>()

  @Test
  fun testCounter() {
    composeTestRule.setContent {
      CounterWithState(id="1")
    }
    composeTestRule.onNodeWithTag("Counter-1").assertExists()
  }
}