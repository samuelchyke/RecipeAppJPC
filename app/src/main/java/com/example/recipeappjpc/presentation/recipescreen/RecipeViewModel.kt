package com.example.recipeappjpc.presentation.recipescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.repository.NetworkRepository
import com.example.recipeappjpc.utils.FoodCategory
import com.example.recipeappjpc.utils.getFoodCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val PAGE_SIZE = 30

data class RecipeScreenUiState(
    val recipe: List<Recipe> = emptyList(),
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

    private val _recipe: MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())
    private val _selectedCategory: MutableStateFlow<FoodCategory?> = MutableStateFlow(null)
    private val _scrollTabPosition = MutableStateFlow(0)
    private val _loading = MutableStateFlow(false)
    private val _query = MutableStateFlow("")
    private val _page = MutableStateFlow(1)
    private var _recipeListScrollPosition = MutableStateFlow(0)

    val __uiState : StateFlow<RecipeScreenUiState> = combine(_recipe, _selectedCategory, _scrollTabPosition, _loading, _query, _page, _recipeListScrollPosition)
    { RecipeScreenUiState(
            recipe = _recipe.value,
            selectedCategory = _selectedCategory.value,
            scrollTabPosition = _scrollTabPosition.value,
            loading = _loading.value,
            query = _query.value,
            page = _page.value,
            recipeListScrollPosition = _recipeListScrollPosition.value
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        RecipeScreenUiState(loading = false)
    )

    private val _uiState = MutableStateFlow(RecipeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        searchRecipes()
    }

    fun searchRecipes() = viewModelScope.launch {
        clearRecipeList()
        startLoading()
        try {
            val response =
                networkRepository.getListOfRecipes(_query.value, _page.value)
            response.body()?.let { recipe ->
                _recipe.value = recipe.results
//                _uiState.update {
//                    it.copy(
//                        recipe = recipe.results
//                    )
//                }
            }
        } catch (e: Exception) {
            Timber.e("launchJob: Exception: $e, ${e.cause}")
            e.printStackTrace()
        }
        stopLoading()
    }

    fun nextPage() {
//        val trgg = (_uiState.value.recipeListScrollPosition + 1) >= (_uiState.value.page * PAGE_SIZE)
        val trgg = (_recipeListScrollPosition.value + 1) >= (_page.value * PAGE_SIZE)
        viewModelScope.launch {
            // prevent duplicate event due to recompose happening to quickly
            if (trgg) {
                Log.d("dc","Next Page Triggered")
                startLoading()
                incrementPage()
                if (_page.value > 1) {
                    val response = networkRepository.getListOfRecipes(
                        _query.value, _page.value
                    )
                    response.body()?.let {
                        appendRecipes(it.results)
                    }
                }
                stopLoading()
            }
        }
    }

    private fun startLoading() {
        _loading.value = true
//        _uiState.update {
//            it.copy(
//                loading = true
//            )
//        }
    }

    private fun stopLoading() {
        _loading.value = false
//        _uiState.update {
//            it.copy(
//                loading = false
//            )
//        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val currentList = ArrayList(_uiState.value.recipe)
        currentList += recipes
        _recipe.value += currentList
//        _uiState.update {
//            it.copy(
//                recipe = currentList
//            )
//        }
    }

    private fun incrementPage() =
        _page.value ++
//        _uiState.update {
//            it.copy(
//                page = +1
//            )
//        }

    fun onChangedRecipeResultPosition(position: Int) {
        _recipeListScrollPosition.value = position
//        _uiState.update {
//            it.copy(
//                recipeListScrollPosition = position
//            )
//        }
    }

    fun onQueryChanged(query: String) {
        _query.value = query
//        _uiState.update {
//            it.copy(
//                query = query
//            )
//        }
    }

    fun onSelectedCategoryChanged(category: String) {
        _page.value = 1
//        _uiState.update {
//            it.copy(
//                page = 1
//            )
//        }
        val newCategory = getFoodCategory(category)
        _selectedCategory.value = newCategory
//        _uiState.update {
//            it.copy(
//                selectedCategory = newCategory
//            )
//        }
        _scrollTabPosition.value = newCategory?.ordinal ?: 0
//        _uiState.update {
//            it.copy(
//                scrollTabPosition = newCategory?.ordinal ?: 0
//            )
//        }
        onQueryChanged(category)
    }

    fun clearSelectedCategory() {
//        val queryNotEqualToSelectedCategory =
//            _uiState.value.query != _uiState.value.selectedCategory?.value

        val queryNotEqualToSelectedCategory =
            _query.value != _selectedCategory.value?.name
        if (queryNotEqualToSelectedCategory) {
            _page.value = 1
//            _uiState.update {
//                it.copy(
//                    page = 1
//                )
//            }
            _selectedCategory.value = null
//            _uiState.update {
//                it.copy(
//                    selectedCategory = null
//                )
//            }
            _scrollTabPosition.value = 0
//            _uiState.update {
//                it.copy(
//                    scrollTabPosition = 0
//                )
//            }
            onChangedRecipeResultPosition(0)
        }
//        val newCategory = getFoodCategory(_uiState.value.query)
        val newCategory = getFoodCategory(_query.value)
        if (newCategory != null) {
            onSelectedCategoryChanged(_query.value)
        }
    }

    private fun clearRecipeList() {
        _recipe.value = emptyList()
//        _uiState.update {
//            it.copy(
//                recipe = listOf()
//            )
//        }
    }
}
