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
                val encodedImageUrl = URLEncoder.encode(
                    recipe.featured_image, StandardCharsets.UTF_8.toString()
                )

                val ingredients = recipe.ingredients?.toTypedArray() ?: emptyArray()
//                recipe.ingredients?.forEach { it
//                    ingredients = arrayOf(it)
//                }
                if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                    onTriggerNextPage()
                }

                val domainRecipe = mapToDomain(recipe)

                val mockDomainRecipe = DomainRecipe(
                    "",
                    "",
                    "",
                    "",
                    listOf(""),
                    "",
                    0,
                    ""
                )

                val test = Test("queifbqwuhfbqohf", emptyList())
                val json = Uri.encode(Gson().toJson(test))

//                val moshi = Moshi.Builder().build()
//                val jsonAdapter = moshi.adapter(Test::class.java).lenient()
//                val userJson = jsonAdapter.toJson(test)

                RecipeCard(recipe = recipe, onClick = {


//                    navController.currentBackStackEntry?.arguments?.putParcelable("recipe", json)
                    navController.navigate("$RECIPE_DETAIL_SCREEN_ROUTE/$json")
//                    navAction.navigateToRecipeDetailScreen(
//                        recipeIngredients = ingredients
////                        recipe
////                        recipe.title,
////                        encodedImageUrl,
////                        recipe.rating.toString(),
////                        recipe.publisher,
////                        recipe.date_added,
//////                        recipe.description,
////                        recipe.cooking_instructions ?: "Unavailable",
////                        recipeIngredients = ingredients
//                    )
                })
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}

