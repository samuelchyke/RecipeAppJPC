package com.example.recipeappjpc.repository

import com.example.recipeappjpc.model.RecipeResult
import com.example.recipeappjpc.network.RecipeServiceApi
import javax.inject.Inject
import retrofit2.Response

interface NetworkRepository {

    suspend fun getListOfRecipes(query: String, page: Int): Response<RecipeResult>
}

class NetworkRepositoryImpl @Inject constructor(
    private val recipeServiceApi: RecipeServiceApi
) : NetworkRepository {

    override suspend fun getListOfRecipes(query: String, page: Int): Response<RecipeResult> {
        return recipeServiceApi.getListOfRecipes(query = query, page = page)
    }
}
