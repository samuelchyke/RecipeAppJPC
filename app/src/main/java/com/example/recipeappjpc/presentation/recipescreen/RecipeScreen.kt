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
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.presentation.navigation.NavigationActions

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipeScreen(
    recipeViewModel: RecipeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navAction: NavigationActions,
) {

    val uiState by recipeViewModel.uiState.collectAsStateWithLifecycle()
    val _uiState by recipeViewModel.__uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Scaffold(scaffoldState = scaffoldState, topBar = {
        SearchAppBar(
            query = _uiState.query,
            selectedCategory = _uiState.selectedCategory,
            selectedChip = _uiState.scrollTabPosition,
            onQueryChanged = recipeViewModel::onQueryChanged,
            onExecuteSearch = recipeViewModel::searchRecipes,
            clearFocus = focusManager::clearFocus,
            onClearSelectedCategory = recipeViewModel::clearSelectedCategory,
            onSelectedCategoryChanged = recipeViewModel::onSelectedCategoryChanged
        )
    }) { paddingValues ->
        RecipeScreenContent(
            modifier = Modifier.padding(paddingValues),
            loading = _uiState.loading,
            recipes = _uiState.recipe,
            onChangeScrollPosition = recipeViewModel::onChangedRecipeResultPosition,
            page = _uiState.page,
            onTriggerNextPage = { recipeViewModel.nextPage() },
            navAction = navAction
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
    navAction: NavigationActions
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
                navAction = navAction
            )
        }
    }
}
