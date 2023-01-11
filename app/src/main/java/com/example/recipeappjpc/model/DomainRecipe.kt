package com.example.recipeappjpc.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainRecipe(
    val cooking_instructions: String,
    val date_updated: String,
    val description: String,
    val featured_image: String,
    val ingredients: List<String>,
    val publisher: String,
    val rating: Int,
    val title: String
) : Parcelable

fun mapToDomain(recipe: Recipe): DomainRecipe =
    DomainRecipe(
        cooking_instructions = recipe.cooking_instructions ?: "",
        date_updated = recipe.date_updated,
        description = recipe.description,
        featured_image = recipe.featured_image ?: "",
        ingredients = recipe.ingredients ?: emptyList(),
        publisher = recipe.publisher,
        rating = recipe.rating,
        title = recipe.title,
    )

