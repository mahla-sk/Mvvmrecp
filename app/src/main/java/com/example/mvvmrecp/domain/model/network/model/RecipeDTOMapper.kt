package com.example.mvvmrecp.domain.model.network.model

import com.example.mvvmrecp.domain.model.Recipe
import com.example.mvvmrecp.domain.model.util.DomainMapper

class RecipeDTOMapper:DomainMapper<RecipeDTO,Recipe>{
    override fun mapToDomainModel(model: RecipeDTO): Recipe {
        return Recipe(
            id=model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            publisher = model.publisher,
            rating = model.rating,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,
            ingredients = model.ingredients?: listOf(),
            dataAdded = model.dateAdded,
            dataUpdated = model.dateUpdated
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDTO {
        return RecipeDTO(
            pk=domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            publisher = domainModel.publisher,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded= domainModel.dataAdded,
            dateUpdated= domainModel.dataUpdated
        )
    }
    fun ToDomainList(initial:List<RecipeDTO>):List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }
    fun FromDomainList(initial:List<Recipe>):List<RecipeDTO>{
        return initial.map { mapFromDomainModel(it) }
    }
}