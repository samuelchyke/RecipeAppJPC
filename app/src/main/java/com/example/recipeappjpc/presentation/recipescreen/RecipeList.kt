package com.example.recipeappjpc.presentation.recipescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.presentation.navigation.NavigationActions
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    navAction: NavigationActions,
) {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.surface)
    ) {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                onChangeScrollPosition(index)
                val encodedImageUrl = URLEncoder.encode(
                    recipe.featured_image, StandardCharsets.UTF_8.toString()
                )
                if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                    onTriggerNextPage()
                }
                RecipeCard(recipe = recipe, onClick = {
                    navAction.navigateToRecipeDetailScreen(recipe.title, encodedImageUrl)
                })
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}

