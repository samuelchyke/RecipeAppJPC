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
) : Parcelable {

//    constructor(parcel: Parcel) : this(
//    parcel.readString(),
//    parcel.readString(),
//    parcel.readString(),
//    parcel.readString(),
//    parcel.createStringArrayList(),
//    parcel.readString(),
//    parcel.readInt(),
//    parcel.readString()
//    )
//
//    companion object : Parceler<DomainRecipe> {
//
//        override fun DomainRecipe.write(parcel: Parcel, flags: Int) {
//            parcel.writeString(cooking_instructions)
//            parcel.writeString(date_updated)
//            parcel.writeString(title)
//            parcel.writeStringArray(ingredients?.toTypedArray())
//            parcel.writeString(publisher)
//            parcel.writeString(description)
//            parcel.writeInt(rating!!)
//            parcel.writeString(title)
//        }
//
//        override fun create(parcel: Parcel): DomainRecipe {
//            return DomainRecipe(parcel)
//        }
//    }
}

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

//    }
//} =
//    this.map { recipe ->
//        DomainRecipe(
//            cooking_instructions = recipe.cooking_instructions
//        )
//    }
//    DomainRecipe(
//        cooking_instructions = recipe.cooking_instructions
//date_added,
//val date_updated: String,
//val description: String,
//val featured_image: String,
//val ingredients: List<String>,
//val long_date_added: Int,
//val long_date_updated: Int,
//val pk: Int,
//val publisher: String,
//val rating: Int,
//val source_url: String,
//val title: String
//    )
