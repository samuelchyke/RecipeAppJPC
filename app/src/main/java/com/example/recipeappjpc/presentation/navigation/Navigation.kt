package com.example.recipeappjpc.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.recipeappjpc.model.DomainRecipe
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.model.Test
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_ROUTE
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.DATE_UPDATED_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.DESCRIPTION_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.IMAGE_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.INGREDIENTS_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.INSTRUCTIONS_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.PUBLISHER_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.RATING_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.RECIPE_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.TEST_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.TITLE_ARG
import com.example.recipeappjpc.utils.AssetParamType

/**
 * Screens used in [Destinations]
 */
sealed class Screens(val route: String, val arguments: List<NamedNavArgument>) {

    object RecipeScreen : Screens("RecipeScreen", emptyList())

    object RecipeDetailScreen : Screens(
        "RecipeDetailScreen",
        arguments = listOf(
//            navArgument("title") {
//                type = NavType.StringType
//            },
//            navArgument("image") {
//                type = NavType.StringType
//            },
//            navArgument("rating") {
//                type = NavType.StringType
//            },
//            navArgument("publisher") {
//                type = NavType.StringType
//            },
//            navArgument("dateUpdated") {
//                type = NavType.StringType
//            },
////            navArgument("description") {
////                type = NavType.StringType
////            },
//            navArgument("instructions") {
//                type = NavType.StringType
//            },
//            navArgument("ingredients") {
//                type = NavType.StringArrayType
//            },
//            navArgument("recipe") {
//                type = NavType.ParcelableType(type = DomainRecipe::class.java)
//            },
                navArgument("test") {
                type = AssetParamType()
            }
        )
    )

}

/**
 * [DestinationsArgs] used in the [NavGraph]
 */

object DestinationsArgs {
    const val TITLE_ARG = "/{title}"
    const val IMAGE_ARG = "/{image}"
    const val RATING_ARG = "/{rating}"
    const val PUBLISHER_ARG = "/{publisher}"
    const val DATE_UPDATED_ARG = "/{dateUpdated}"
    const val DESCRIPTION_ARG = "/{description}"
    const val INGREDIENTS_ARG = "/{ingredients}"
    const val INSTRUCTIONS_ARG = "/{instructions}"
    const val RECIPE_ARG = "/{recipe}"
    const val TEST_ARG = "/{test}"
}

/**
 * [Destinations] used in the [NavigationActions]
 */
object Destinations {
    val RECIPE_ROUTE = Screens.RecipeScreen.route
    val RECIPE_DETAIL_SCREEN_ROUTE = Screens.RecipeDetailScreen.route
    val RECIPE_DETAIL_SCREEN_ROUTE_ARGUMENTS = buildString {
        append(RECIPE_DETAIL_SCREEN_ROUTE)
//        append(TITLE_ARG)
//        append(RECIPE_ARG)
//        append(IMAGE_ARG)
//        append(RATING_ARG)
//        append(PUBLISHER_ARG)
//        append(DATE_UPDATED_ARG)
////        append(DESCRIPTION_ARG)
//        append(INSTRUCTIONS_ARG)
//        append(INGREDIENTS_ARG)
        append(TEST_ARG)
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
//        vararg recipeArguments: String,
//        recipeArguments: Recipe,
        recipeIngredients: Array<String>
    ) {
        val route = buildString {
            append(RECIPE_DETAIL_SCREEN_ROUTE)
//            recipeArguments.forEach { args ->
//                append("/$args")
//            }
//            append("/${recipeArguments}")
//            append("/${recipeIngredients}")
        }
        navController.navigate(route)
    }

}
