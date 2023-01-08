package com.example.recipeappjpc.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeappjpc.presentation.recipedetailscreen.RecipeDetailScreen
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE
import com.example.recipeappjpc.presentation.navigation.DestinationsArgs.IMAGE_ARG
import com.example.recipeappjpc.presentation.recipescreen.RecipeScreen
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
                navAction = navActions
            )
        }

        composable(
            route = RECIPE_DETAIL_SCREEN_ROUTE + TITLE_ARG + IMAGE_ARG,
            arguments = Screens.RecipeDetailScreen.arguments
            ) { entry ->
            RecipeDetailScreen(
                onBack = { navController.popBackStack()},
                entry = entry
            )
        }
    }
}



