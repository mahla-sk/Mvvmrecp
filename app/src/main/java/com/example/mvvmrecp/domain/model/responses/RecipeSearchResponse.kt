package com.example.mvvmrecp.domain.model.responses

import com.example.mvvmrecp.domain.model.network.model.RecipeDTO
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("results")
    val recipes:List<RecipeDTO>,
        )