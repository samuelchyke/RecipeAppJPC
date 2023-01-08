package com.example.recipeappjpc.presentation.recipedetailscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun RecipeDetailScreen(
    onBack: () -> Unit,
    entry : NavBackStackEntry,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                navBack = { onBack() }
            )
        }
    ) { paddingValues ->

        RecipeDetailScreenContent(
            modifier = Modifier.padding(paddingValues),
            recipeImage = entry.arguments?.getString("image")!!,
            recipeTitle = entry.arguments?.getString("title")!!,
        )
    }
}

@Composable
fun RecipeDetailScreenContent(
    modifier: Modifier = Modifier,
    recipeImage : String,
    recipeTitle : String

) {
    val scrollState = rememberScrollState()

    val screenPadding = Modifier.padding(
        horizontal = 0.dp,
        vertical = 0.dp,
    )
    val commonModifier = modifier
        .fillMaxWidth()
        .then(screenPadding)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(commonModifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            CoilImage(
                imageModel = recipeImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
//                recipe.title.let { title ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = recipeTitle,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h5
                        )
//                        val rank = recipe.rating.toString()
//                        Text(
//                            text = rank,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentWidth(Alignment.End)
//                                .align(Alignment.CenterVertically),
//                            style = MaterialTheme.typography.h5
//                        )
                    }
//                }
//                recipe.publisher.let { publisher ->
//                    val updated = recipe.date_updated
//                    Text(
//                        text = "Updated $updated by $publisher",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 8.dp),
//                        style = MaterialTheme.typography.caption
//                    )
//                }
//                recipe.description.let { description ->
//                    if (description != "N/A") {
//                        Text(
//                            text = description,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 8.dp),
//                            style = MaterialTheme.typography.body1
//                        )
//                    }
//                }
//
//                Text(
//                    text = "Ingredients",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 4.dp)
//                        .wrapContentWidth(Alignment.CenterHorizontally),
//                    style = MaterialTheme.typography.body1
//                )
//
//                for (ingredient in recipe.ingredients!!) {
//                    Text(
//                        text = ingredient,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 4.dp),
//                        style = MaterialTheme.typography.body1
//                    )
//                }
//
//                recipe.cooking_instructions?.let { instructions ->
//
//                    Text(
//                        text = "Cooking Instructions",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 4.dp)
//                            .wrapContentWidth(Alignment.CenterHorizontally),
//                        style = MaterialTheme.typography.body1
//                    )
//
//                    Text(
//                        text = instructions,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 4.dp),
//                        style = MaterialTheme.typography.body1
//                    )
                }
            }
        }
    }


@Preview
@Composable
fun MainScreenPreview() {
    AppCompatTheme {
        Surface {

        }
    }
}







