package com.example.recipeappjpc.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeappjpc.presentation.RecipeDetailScreen
import com.example.recipeappjpc.presentation.recipescreen.RecipeScreen
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.DESCRIPTION_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.INGREDIENTS_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.INSTRUCTIONS_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.PUBLISHER_ARG
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.TITLE_ARG

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.RECIPE_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Destinations.RECIPE_ROUTE) {
            RecipeScreen(
                navController = navController
            )
        }


        composable(
            route = Destinations.RECIPE_DETAIL_SCREEN_ROUTE,
            arguments = listOf(
                navArgument(TITLE_ARG) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument (PUBLISHER_ARG){
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument (DESCRIPTION_ARG){
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument (INGREDIENTS_ARG){
                    type = NavType.StringArrayType
                    defaultValue = ""
                },
                navArgument (INSTRUCTIONS_ARG){
                    type = NavType.StringType
                    defaultValue = ""
                },
//                navArgument (RATING_ARG){
//                    type = NavType.IntType
//                    defaultValue = 0
//                }
            )
        ) { entry ->
            RecipeDetailScreen(
                onBack = { navController.popBackStack()}
            )
        }
    }
}



