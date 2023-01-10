package com.example.recipeappjpc.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.example.recipeappjpc.model.Test
import com.google.gson.Gson

class AssetParamType : NavType<Test>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Test? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Test {
        return Gson().fromJson(value, Test::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Test) {
        bundle.putParcelable(key, value)
    }
}