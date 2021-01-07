package com.sw.mobile.flickrbrowser.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sw.mobile.flickrbrowser.R
import com.sw.mobile.flickrbrowser.model.FlickrItem
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

@Composable
fun FlickrRow(flickrItem: FlickrItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.clickable(
            onClick = {/**/ }
        ).padding(bottom = 4.dp).clip(RoundedCornerShape(4.dp))
            .background(color = MaterialTheme.colors.surface).padding(8.dp).fillMaxWidth()

    ) {
        Surface(
            modifier = Modifier.preferredSize(50.dp).clip(CircleShape),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                imageResource(R.drawable.moon1),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                flickrItem.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1
            )
            Providers(AmbientContentAlpha provides ContentAlpha.disabled) {
                Text(
                    flickrItem.formattedDate,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFlickrRow() =
    FlickrRow(FlickrItem(id = "1", title = "The Moon", date = "2018-12-12 14:05:33", imageUrl = ""))

//@Preview
//@Composable
//fun PreviewFlickrRowDark() {
//    FlickrbrowserTheme(darkTheme = true) {
//        FlickrRow(
//            FlickrItem(
//                id = "1",
//                title = "The Moon",
//                date = "2018-12-12 14:05:33",
//                imageUrl = ""
//            )
//        )
//    }
//}
