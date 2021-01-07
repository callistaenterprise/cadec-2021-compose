package com.sw.mobile.flickrbrowser.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sw.mobile.flickrbrowser.model.AmbientFlickrSearch

@Composable
fun SearchInputText(
    text: String? = null,
    onImeAction: () -> Unit = {}
) {
    val flickrViewModel = AmbientFlickrSearch.current

    var textVal by savedInstanceState { text ?: flickrViewModel.searchText }
//    val (selected, onSelected) = remember { mutableStateOf(false) }
//    val animatedSelection = animate(if (selected) 4.dp else 1.dp, TweenSpec(500))
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
                println("---- Search text = " + textVal)
                flickrViewModel.onSearchText(textVal)
                flickrViewModel.onSearchFlickr(textVal)
            }
        },
        trailingIcon = {if (flickrViewModel.loading) Icon(Icons.Outlined.Cake) else Icon(Icons.Outlined.Cake)},

        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.secondaryVariant).padding(8.dp)
    )
}

@ExperimentalFocus
@Preview
@Composable
fun PreviewIconRow() = SearchInputText(text = "Space")