package com.example.recipeappjpc.repository

import com.example.recipeappjpc.model.Recipe
import com.example.recipeappjpc.model.RecipeResult
import com.example.recipeappjpc.network.RecipeServiceApi
import retrofit2.Response
import javax.inject.Inject

interface NetworkRepository {

    suspend fun getListOfRecipes(query: String, page: Int) : Response<RecipeResult>

    suspend fun getRecipe(id : Int) : Response<Recipe>

}

class NetworkRepositoryImpl @Inject constructor(
    private val recipeServiceApi: RecipeServiceApi
):NetworkRepository{

    override suspend fun getListOfRecipes(query: String, page : Int): Response<RecipeResult> {
        return recipeServiceApi.getListOfRecipes(query =  query, page = page)
    }

    override suspend fun getRecipe(id: Int): Response<Recipe> {
        return recipeServiceApi.getRecipe(id = id)
    }

}