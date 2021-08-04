package com.example.mvvmrecp

sealed class RecipeListEvents {
    object newSearchEvent: RecipeListEvents()
    object nextPageEvent :RecipeListEvents()
    object restoreStateEvent:RecipeListEvents()
}