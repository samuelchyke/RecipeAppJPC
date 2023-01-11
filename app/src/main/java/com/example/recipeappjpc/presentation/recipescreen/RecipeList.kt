package com.example.recipeappjpc.presentation.recipescreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import com.example.recipeappjpc.model.DomainRecipe
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.model.Test
import com.example.recipeappjpc.model.mapToDomain
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE_ARGUMENTS
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_ROUTE
import com.example.recipeappjpc.presentation.navigation.NavigationActions
import com.example.recipeappjpc.ui.theme.Teal200
import com.google.gson.Gson
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

                val test = Test("queifbqwuhfbqohf", emptyList())
                val json = Uri.encode(Gson().toJson(test))

                RecipeCard(recipe = recipe, onClick = {

                    val recipeJson = Uri.encode(Gson().toJson(recipe))

                    navController.navigate("$RECIPE_DETAIL_SCREEN_ROUTE/$json")
                })
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}

