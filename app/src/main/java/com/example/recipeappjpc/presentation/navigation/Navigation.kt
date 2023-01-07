package com.example.recipeappjpc.presentation.navigation

import androidx.navigation.NavHostController
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_ROUTE
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.DESCRIPTION_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.INGREDIENTS_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.INSTRUCTIONS_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.PUBLISHER_ARG
//import com.example.reappearance.presentation.navigation.DestinationsArgs.RATING_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.TITLE_ARG


/**
 * Screens used in [Destinations]
 */
sealed class Screens(val route: String){
    object RecipeScreen : Screens("RecipeScreen")
    object RecipeDetailScreen : Screens("RecipeDetailScreen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}

object DestinationsArgs {
    const val TITLE_ARG = "recipe"
    const val PUBLISHER_ARG = "publisher"
    const val INGREDIENTS_ARG = "ingredients"
    const val INSTRUCTIONS_ARG = "instructions"
    const val DESCRIPTION_ARG = "description"
//    const val RATING_ARG = "rating"
}

/**
 * [Destinations] used in the [NavigationActions]
 */
object Destinations {
    val RECIPE_ROUTE = Screens.RecipeScreen.route
    val RECIPE_DETAIL_SCREEN_ROUTE = Screens.RecipeDetailScreen.withArgs(TITLE_ARG, PUBLISHER_ARG, DESCRIPTION_ARG, INGREDIENTS_ARG, INSTRUCTIONS_ARG)
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToRecipeScreen() {
        navController.navigate(RECIPE_ROUTE)
    }

    fun navigateToRecipeDetailScreen(vararg recipeArg:String, recipeRating : Int = 0) {
        val route = buildString {
            append(RECIPE_DETAIL_SCREEN_ROUTE)
            recipeArg.forEach { args ->
                append("/$args")
//                append(recipeRating)
            }
        }
        navController.navigate(route)
    }

}