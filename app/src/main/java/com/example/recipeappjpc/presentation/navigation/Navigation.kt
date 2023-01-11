package com.example.recipeappjpc.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_ROUTE
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.RECIPE_ARG
import com.example.recipeappjpc.utils.RecipeNavType

/**
 * Screens used in [Destinations]
 */
sealed class Screens(val route: String, val arguments: List<NamedNavArgument>) {

    object RecipeScreen : Screens("RecipeScreen", emptyList())

    object RecipeDetailScreen : Screens(
        "RecipeDetailScreen",
        arguments = listOf(
                navArgument("recipe") {
                type = RecipeNavType()
            }
        )
    )

}

/**
 * [DestinationsArgs] used in the [NavGraph]
 */

object DestinationsArgs {
    const val RECIPE_ARG = "/{recipe}"
}

/**
 * [Destinations] used in the [NavigationActions]
 */
object Destinations {
    val RECIPE_ROUTE = Screens.RecipeScreen.route
    val RECIPE_DETAIL_SCREEN_ROUTE = Screens.RecipeDetailScreen.route
    val RECIPE_DETAIL_SCREEN_ROUTE_ARGUMENTS = buildString {
        append(RECIPE_DETAIL_SCREEN_ROUTE)
        append(RECIPE_ARG)
    }
    val RECIPE_DETAIL_SCREEN_ARGUMENTS = Screens.RecipeDetailScreen.arguments
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToRecipeScreen() {
        navController.navigate(RECIPE_ROUTE)
    }

    fun navigateToRecipeDetailScreen(
        recipeArgument: String,
    ) {
        val route = buildString {
            append(RECIPE_DETAIL_SCREEN_ROUTE)
            append("/${recipeArgument}")
        }
        navController.navigate(route)
    }

}
