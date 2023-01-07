package com.example.recipeappjpc.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.google.accompanist.appcompattheme.AppCompatTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipeDetailScreen(
    onBack: () -> Unit,
    mainViewModel: RecipeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
//            AppBar(
//                navBack = { onBack() }
//            )
        }
    ) { paddingValues ->

//        val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
        MainScreenContent(
            modifier = Modifier.padding(paddingValues),
//            photos = uiState.photos
        )
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
//    photos: List<PhotoItem>,
) {
    val screenPadding = Modifier.padding(
        horizontal = 10.dp,
        vertical = 10.dp,
    )
    val commonModifier = modifier
        .fillMaxWidth()
        .then(screenPadding)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(commonModifier)
    ) {
//        PhotoList(
//            photos = photos
//        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppCompatTheme {
        Surface {
            MainScreenContent(
//                photos = listOf(
//                    PhotoItem(
//                        albumId = 0,
//                        id = 0,
//                        thumbnailUrl = "",
//                        title = "",
//                        url = ""
//                    )
//                )
            )
        }
    }
}






