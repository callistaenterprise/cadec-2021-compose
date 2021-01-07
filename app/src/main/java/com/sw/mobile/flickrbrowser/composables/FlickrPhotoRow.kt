package com.sw.mobile.flickrbrowser.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import com.sw.mobile.flickrbrowser.R
import com.sw.mobile.flickrbrowser.model.Photo
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun FlickrPhotoRow(navController: NavHostController, photo: Photo, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.clickable(
            onClick = { navController.navigate("photoDetails/${photo.url_l}") }
        ).padding(bottom = 4.dp).clip(RoundedCornerShape(4.dp))
            .background(color = MaterialTheme.colors.surface).padding(8.dp).fillMaxWidth()

    ) {
        Surface(
            modifier = Modifier.preferredSize(80.dp).clip(RoundedCornerShape(6.dp)),
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            if (photo.url_m != "") GlideImage(
                imageModel = photo.url_m,
                modifier = modifier,
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
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Providers(AmbientContentAlpha provides ContentAlpha.disabled) {
                Text(
                    photo.title,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFlickrPhotoRow() {
    val navController = rememberNavController()
    FlickrPhotoRow(
        navController = navController,
        Photo(
            id = "50718869598",
            url_m = "https://live.staticflickr.com/65535/50718869598_b2f2c029d8.jpg",
            title = "A7403526-Edit"
        )
    )
}

