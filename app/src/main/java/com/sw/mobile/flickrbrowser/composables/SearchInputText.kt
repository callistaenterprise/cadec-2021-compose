package com.sw.mobile.flickrbrowser.components

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.accessibilityLabel
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sw.mobile.flickrbrowser.model.AmbientFlickrSearch
import com.sw.mobile.flickrbrowser.model.FlickrViewModel
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

@Composable
fun SearchInputTextContainer() {
  val flickrViewModel = AmbientFlickrSearch.current
  SearchInputText(
    searchText = flickrViewModel.searchText,
    loading = flickrViewModel.loading,
    onSearchFlickr = flickrViewModel::onSearchFlickr
  )
}

@Composable
fun SearchInputText(
  modifier: Modifier = Modifier,
  searchText: String = "",
  loading: Boolean,
  onSearchFlickr: (text: String) -> Unit,
  onImeAction: () -> Unit = {},
) {
  var textVal by remember { mutableStateOf(searchText) }
  onCommit(textVal, {
    onSearchFlickr(textVal)
  })
  Row(modifier = modifier.fillMaxWidth().background(MaterialTheme.colors.surface)) {
    OutlinedTextField(
      value = textVal,
      onValueChange = {
        textVal = it
      },
      activeColor = MaterialTheme.colors.primary,
      inactiveColor = MaterialTheme.colors.secondary,
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      onImeActionPerformed = { action, softKeyboardController ->
        if (action == ImeAction.Done) {
          onImeAction()
          softKeyboardController?.hideSoftwareKeyboard()
        }
      },
      trailingIcon = {
        if (loading)
          Icon(
            Icons.Filled.Cake,
            modifier = modifier.testTag("icon-loading").clickable(onClick = { textVal = ""})
          )
        else
          Icon(
            Icons.Outlined.Cake,
            modifier = modifier.testTag("icon-not-loading").clickable(onClick = { textVal = ""})
          )
      },
      modifier = modifier.fillMaxWidth().padding(8.dp).testTag("search-input")
    )
  }
}

@Preview
@Composable
fun SearchInputTextPreview() {
  SearchInputText(searchText = "Rocket has Loaded",
    loading = false, onSearchFlickr = { /*TODO*/ })
}

@Preview
@Composable
fun SearchInputTextPreviewLoading() {
  SearchInputText(searchText = "Rocket is Loading",
    loading = true, onSearchFlickr = { /*TODO*/ })
}

@Preview
@Composable
fun SearchInputTextPreviewLoadingTheme() {
  FlickrbrowserTheme {
    SearchInputText(searchText = "With Theme And Loading",
      loading = true, onSearchFlickr = { /*TODO*/ })
  }
}

@Preview
@Composable
fun SearchInputTextContainerPreviewLoadingTheme() {
  val flickrViewModel = FlickrViewModel(
    _loading = true,
    _searchText = "With Ambient, it's a Context!!"
  )
  FlickrbrowserTheme {
    Providers(AmbientFlickrSearch provides flickrViewModel) {
      SearchInputTextContainer()
    }
  }
}
