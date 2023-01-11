package com.example.recipeappjpc.presentation.recipedetailscreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.recipeappjpc.model.Recipe
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RecipeDetailScreen(
    onBack: () -> Unit,
    entry: NavBackStackEntry,
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

        val recipe = entry.arguments?.getParcelable("recipe", Recipe::class.java)!!

        RecipeDetailScreenContent(
            modifier = Modifier.padding(paddingValues),
            recipeImage = recipe.featured_image ?: "",
            recipeTitle = recipe.title,
            recipeRating = recipe.rating.toString(),
            recipePublisher = recipe.publisher,
            recipeDateUpdated = recipe.date_updated,
            recipeDescription = recipe.description,
            recipeIngredients = recipe.ingredients ?: emptyList(),
            recipeInstructions = recipe.cooking_instructions ?: "N/A"
        )
    }
}

@Composable
fun RecipeDetailScreenContent(
    modifier: Modifier = Modifier,
    recipeImage: String,
    recipeTitle: String,
    recipeRating: String,
    recipePublisher: String,
    recipeDateUpdated: String,
    recipeDescription: String,
    recipeIngredients: List<String>,
    recipeInstructions: String,
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
                    Text(
                        text = recipeRating,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }
                Text(
                    text = "Updated $recipeDateUpdated by $recipePublisher",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.caption
                )
                if (recipeDescription != "N/A") {
                    Text(
                        text = recipeDescription,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(
                    text = "Ingredients",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body1
                )
                for (ingredient in recipeIngredients) {
                    Text(
                        text = "- $ingredient",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(
                    text = "Cooking Instructions",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = recipeInstructions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    style = MaterialTheme.typography.body1
                )
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







