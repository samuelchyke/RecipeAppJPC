package com.example.recipeappjpc.presentation.recipescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.presentation.PAGE_SIZE
import com.example.recipeappjpc.presentation.navigation.Screens

@Composable
    fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
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
                RecipeCard(
                    recipe = recipe,
                    onClick = {
//                            val nav = RecipeListFragmentDirections.navToRecipeFragment(recipe)
                        navController.navigate(
                            Screens.RecipeDetailScreen.withArgs(
                                recipe.title,
                                recipe.publisher,
                                recipe.description,
                                recipe.ingredients.toString(),
                                recipe.cooking_instructions.toString(),
//                                    recipe.rating
                            )
                        )
                    }
                )
            }
        }
//        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}

