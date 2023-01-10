package com.example.recipeappjpc.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.example.recipeappjpc.model.Recipe
import com.google.gson.Gson
//
//val RecipeType = object : NavType<Recipe>(
//  isNullableAllowed = false
//) {
//  override fun put(bundle: Bundle, key: String, value: Recipe) {
//    bundle.putParcelable(key, value)
//  }
//  override fun get(bundle: Bundle, key: String): Recipe {
//    return bundle.getParcelable(key) as Recipe
//  }
//
//  override fun parseValue(value: String): Recipe {
//    return Gson.decodeFromString<Recipe>(value)
//  }
//
//  // Only required when using Navigation 2.4.0-alpha07 and lower
//  override val name = "SearchParameters"
//}