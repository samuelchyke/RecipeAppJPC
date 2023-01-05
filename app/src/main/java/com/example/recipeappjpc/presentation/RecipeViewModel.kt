package com.example.recipeappjpc.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.repository.NetworkRepository
import com.example.recipeappjpc.utils.FoodCategory
import com.example.recipeappjpc.utils.getFoodCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val recipe: MutableState<List<Recipe>> = mutableStateOf(listOf())

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    val scrollTabPosition = mutableStateOf(0)

    val loading = mutableStateOf(false)

    val query = mutableStateOf("")

    val page = mutableStateOf(1)

    private var recipeListScrollPosition = 0

    init {
        searchRecipes()
    }

    fun searchRecipes() = viewModelScope.launch {
        loading.value = true
        clearRecipeList()
        val response = networkRepository.getListOfRecipes(query.value,page.value)
        response.body()?.let {
            recipe.value = it.results
        }
        loading.value = false
    }

    fun nextPage(){
        viewModelScope.launch {
            // prevent duplicate event due to recompose happening to quickly
            if((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE) ){
                loading.value = true
                incrementPage()

                if(page.value > 1){
                    val response = networkRepository.getListOfRecipes(query.value,page.value)
                    response.body()?.let {
                        appendRecipes(it.results)
                    }
                }
                loading.value = false
            }
        }
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val currentList = ArrayList(this.recipe.value)
        currentList.addAll(recipes)
        this.recipe.value = currentList
    }

    private fun incrementPage(){
        page.value ++
    }

    fun onChangedRecipeResultPosition(position: Int){
        recipeListScrollPosition = position
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        this.page.value = 1
        val newCategory = getFoodCategory(category)
        this.selectedCategory.value = newCategory
        this.scrollTabPosition.value = newCategory?.ordinal ?: 0
        onQueryChanged(category)
    }

    fun clearSelectedCategory() {
        if (query.value != selectedCategory.value?.value){
            this.selectedCategory.value = null
            this.scrollTabPosition.value = 0
            this.page.value = 1
            onChangedRecipeResultPosition(0)
        }
        val newCategory = getFoodCategory(query.value)
        if (newCategory != null){
            onSelectedCategoryChanged(query.value)
        }
    }

    private fun clearRecipeList(){
        recipe.value = listOf()
    }

}

