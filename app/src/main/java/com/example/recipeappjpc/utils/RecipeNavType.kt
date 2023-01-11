package com.example.recipeappjpc.utils

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.example.recipeappjpc.model.Recipe
import com.google.gson.Gson

class RecipeNavType : NavType<Recipe>(isNullableAllowed = false) {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun get(bundle: Bundle, key: String): Recipe? {
        return bundle.getParcelable(key, Recipe::class.java)
    }

    override fun parseValue(value: String): Recipe {
        return Gson().fromJson(value, Recipe::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Recipe) {
        bundle.putParcelable(key, value)
    }
}