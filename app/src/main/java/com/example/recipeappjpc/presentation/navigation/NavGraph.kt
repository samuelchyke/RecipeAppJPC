package com.example.recipeappjpc.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ARGUMENTS
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_DETAIL_SCREEN_ROUTE_ARGUMENTS
import com.example.recipeappjpc.presentation.navigation.Destinations.RECIPE_ROUTE
import com.example.recipeappjpc.presentation.recipedetailscreen.RecipeDetailScreen
import com.example.recipeappjpc.presentation.recipescreen.RecipeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = RECIPE_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = RECIPE_ROUTE) {
            RecipeScreen(
                navAction = navActions
            )
        }

        composable(
            route = RECIPE_DETAIL_SCREEN_ROUTE_ARGUMENTS,
            arguments = RECIPE_DETAIL_SCREEN_ARGUMENTS
        ) { entry ->
            RecipeDetailScreen(
                onBack = { navController.popBackStack() },
                entry = entry
            )
        }
    }
}
