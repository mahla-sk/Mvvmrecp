package com.example.mvvmrecp

sealed class RecipeEvents {
    data class GetRecipeEvent(val id:Int):RecipeEvents()
}