package com.example.recipeappjpc.network

import com.example.recipeappjpc.model.RecipeResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeServiceApi {

    @GET(RECIPE_SEARCH_PATH)
    suspend fun getListOfRecipes(
        @Header("Authorization") token: String = AUTH_TOKEN,
        @Query("page") page: Int = 1,
        @Query("query") query: String,
    ): Response<RecipeResult>

    companion object {

        const val BASE_URL = "https://food2fork.ca/api/recipe/"

        private const val RECIPE_SEARCH_PATH = "search"
        private const val AUTH_TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}
