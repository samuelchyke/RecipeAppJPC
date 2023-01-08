package com.example.recipeappjpc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.repository.NetworkRepository
import com.example.recipeappjpc.utils.FoodCategory
import com.example.recipeappjpc.utils.getFoodCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 30

data class RecipeScreenUiState(
    val recipe: List<Recipe> = listOf(),
    val selectedCategory: FoodCategory? = null,
    val scrollTabPosition: Int = 0,
    val loading: Boolean = false,
    val query: String = "",
    val page: Int = 1,
    var recipeListScrollPosition: Int = 0,
)

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        searchRecipes()
    }

    fun searchRecipes() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        clearRecipeList()
        try {
            val response =
                networkRepository.getListOfRecipes(_uiState.value.query, _uiState.value.page)
            response.body()?.let { recipe ->
                _uiState.update {
                    it.copy(
                        recipe = recipe.results
                    )
                }
            }
        } catch (e: Exception) {

        }
        _uiState.update {
            it.copy(
                loading = false
            )
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            // prevent duplicate event due to recompose happening to quickly
            if ((_uiState.value.recipeListScrollPosition + 1) >= (_uiState.value.page * PAGE_SIZE)) {
                _uiState.update {
                    it.copy(
                        loading = true
                    )
                }
                incrementPage()

                if (_uiState.value.page > 1) {
                    val response = networkRepository.getListOfRecipes(_uiState.value.query, _uiState.value.page)
                    response.body()?.let {
                        appendRecipes(it.results)
                    }
                }
                _uiState.update {
                    it.copy(
                        loading = false
                    )
                }
            }
        }
    }

    //
    private fun appendRecipes(recipes: List<Recipe>) {
        val currentList = ArrayList(_uiState.value.recipe)
        currentList += recipes
        _uiState.update {
            it.copy(
                recipe = currentList
            )
        }
    }

    private fun incrementPage() {
        _uiState.update {
            it.copy(
                page = +1
            )
        }
    }

    fun onChangedRecipeResultPosition(position: Int) {
        _uiState.update {
            it.copy(
                recipeListScrollPosition = position
            )
        }
    }


    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                query = query
            )
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        _uiState.update {
            it.copy(
                page = 1
            )
        }
        val newCategory = getFoodCategory(category)
        _uiState.update {
            it.copy(
                selectedCategory = newCategory
            )
        }
        _uiState.update {
            it.copy(
                scrollTabPosition = newCategory?.ordinal ?: 0
            )
        }
        onQueryChanged(category)
    }


    fun clearSelectedCategory() {
        val queryNotEqualToSelectedCategory =
            _uiState.value.query != _uiState.value.selectedCategory?.value
        if (queryNotEqualToSelectedCategory) {
            _uiState.update {
                it.copy(
                    page = 1
                )
            }
            _uiState.update {
                it.copy(
                    selectedCategory = null
                )
            }
            _uiState.update {
                it.copy(
                    scrollTabPosition = 0
                )
            }
            onChangedRecipeResultPosition(0)
        }
        val newCategory = getFoodCategory(_uiState.value.query)
        if (newCategory != null) {
            onSelectedCategoryChanged(_uiState.value.query)
        }
    }

    private fun clearRecipeList() {
        _uiState.update {
            it.copy(
                recipe = listOf()
            )
        }
    }

}

