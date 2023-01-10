package com.example.recipeappjpc.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Test(
    val a : String?,
    val aa : List<String>?,
//    val aa : String
) : Parcelable
