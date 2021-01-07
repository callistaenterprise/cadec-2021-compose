package com.sw.mobile.flickrbrowser.composables

import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberPin
import androidx.compose.material.icons.outlined.AmpStories
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.sw.mobile.flickrbrowser.components.SearchInputText
import com.sw.mobile.flickrbrowser.model.FlickrItem
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

@Composable
fun LayoutFlickr() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Flickr Compose")
            },
                actions = {
                    IconButton(onClick = { /*doSomthing()*/ }) {
                        Icon(Icons.Filled.FiberPin)
                    }
                })
        },
        bottomBar = {
            BottomNavigation() {
                BottomNavigationItem(
                    icon = { IconButton(onClick = {}) { Icon(Icons.Outlined.Cake) } },
                    selected = false,
                    onClick = {})
                BottomNavigationItem(
                    icon = { IconButton(onClick = {}) { Icon(Icons.Outlined.AmpStories) } },
                    selected = false,
                    onClick = {})
            }
        },
    ) { innerPadding ->
        NavHost(navController, startDestination = "photos") {
            composable("photos") {
                BodyContent(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding).padding(8.dp)
                )
            }
            composable("photoDetails/{url}") { backStackEntry ->
                FlickrPhotoDetails(
                    navController = navController,
                    url = backStackEntry.arguments?.getString("url")
                )
            }
        }
    }
}

@Composable
fun BodyContent(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        SearchInputText()
        FlickrPhotoList(navController = navController)
    }
}

@Preview
@Composable
fun PreviewLayout() =
    FlickrbrowserTheme {
        LayoutFlickr()

    }

