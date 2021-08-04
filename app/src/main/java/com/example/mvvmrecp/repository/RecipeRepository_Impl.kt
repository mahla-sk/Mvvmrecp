package com.example.mvvmrecp.repository

import com.example.mvvmrecp.domain.model.Recipe
import com.example.mvvmrecp.domain.model.network.model.RecipeDTOMapper
import com.example.mvvmrecp.domain.model.network.model.ReciperetrofitService

class RecipeRepository_Impl(
    private val recipeservice:ReciperetrofitService,
    private val mapper:RecipeDTOMapper
):RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result=recipeservice.search(token, page, query).recipes
        return mapper.ToDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeservice.get(token, id))
    }

}