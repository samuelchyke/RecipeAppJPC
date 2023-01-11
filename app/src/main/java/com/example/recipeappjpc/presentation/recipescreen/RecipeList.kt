package com.example.recipeappjpc.presentation.recipescreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.presentation.navigation.NavigationActions
import com.google.gson.Gson

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    navAction: NavigationActions,
    navController: NavController,
) {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.surface)
    ) {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                onChangeScrollPosition(index)

                if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                    onTriggerNextPage()
                }

                RecipeCard(recipe = recipe, onClick = {
                    val recipeJson = Uri.encode(Gson().toJson(recipe))
                    navAction.navigateToRecipeDetailScreen(recipeJson)
                })
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}

