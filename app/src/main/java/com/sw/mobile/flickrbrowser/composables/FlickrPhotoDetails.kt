package com.sw.mobile.flickrbrowser.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FlickrPhotoDetails(navController: NavHostController, url: String? = "") {
    GlideImage(
        imageModel = url ?: "",
        modifier = Modifier.clip(RoundedCornerShape(6.dp)).fillMaxWidth().fillMaxHeight().clickable(
            onClick = { navController.popBackStack() }),
        // shows a progress indicator when loading an image.
        loading = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val indicator = createRef()
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(indicator) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        },
        // shows an error text message when request failed.
        failure = {
            Text(text = "image request failed.")
        })
}
