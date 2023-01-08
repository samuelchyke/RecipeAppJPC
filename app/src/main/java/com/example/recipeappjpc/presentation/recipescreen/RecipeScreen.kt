package com.example.recipeappjpc.presentation.recipescreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.presentation.RecipeViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipeScreen(
    recipeViewModel: RecipeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavController
) {

    val uiState by recipeViewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Scaffold(scaffoldState = scaffoldState, topBar = {
        SearchAppBar(
            query = uiState.query,
            selectedCategory = uiState.selectedCategory,
            selectedChip = uiState.scrollTabPosition,
            onQueryChanged = recipeViewModel::onQueryChanged,
            onExecuteSearch = recipeViewModel::searchRecipes,
            clearFocus = focusManager::clearFocus,
            onClearSelectedCategory = recipeViewModel::clearSelectedCategory,
            onSelectedCategoryChanged = recipeViewModel::onSelectedCategoryChanged
        )
    }) { paddingValues ->
        RecipeScreenContent(
            modifier = Modifier.padding(paddingValues),
            loading = uiState.loading,
            recipes = uiState.recipe,
            onChangeScrollPosition =  recipeViewModel::onChangedRecipeResultPosition,
            page = uiState.page,
            onTriggerNextPage = {recipeViewModel.nextPage()},
            navController = navController,
        )
    }
}

@Composable
private fun RecipeScreenContent(
    modifier: Modifier = Modifier,
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    navController: NavController,
) {
    val screenPadding = Modifier.padding(
        horizontal = 5.dp,
        vertical = 1.dp,
    )
    val commonModifier = modifier
        .fillMaxWidth()
        .then(screenPadding)
    Surface(
        modifier = commonModifier,
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            RecipeList(
                loading = loading,
                recipes = recipes,
                onChangeScrollPosition = { onChangeScrollPosition(it) },
                page = page,
                onTriggerNextPage = { onTriggerNextPage() },
                navController = navController
            )
        }
    }
}
