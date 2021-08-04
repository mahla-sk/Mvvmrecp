package com.example.mvvmrecp.di

import com.example.mvvmrecp.domain.model.network.model.RecipeDTOMapper
import com.example.mvvmrecp.domain.model.network.model.ReciperetrofitService
import com.example.mvvmrecp.repository.RecipeRepository
import com.example.mvvmrecp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
@Singleton
@Provides
fun provideRecipeRepsitory(reciperetrofitService: ReciperetrofitService
,recipeDTOMapper: RecipeDTOMapper):RecipeRepository{
    return RecipeRepository_Impl(reciperetrofitService,recipeDTOMapper)
}
}