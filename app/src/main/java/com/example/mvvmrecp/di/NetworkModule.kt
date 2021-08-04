package com.example.mvvmrecp.di

import com.example.mvvmrecp.domain.model.network.model.RecipeDTOMapper
import com.example.mvvmrecp.domain.model.network.model.ReciperetrofitService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRecipeMapper():RecipeDTOMapper{
        return RecipeDTOMapper()
    }
    @Singleton
    @Provides
    fun provideRecipeService():ReciperetrofitService{
        return Retrofit.Builder().baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ReciperetrofitService::class.java)
    }
    @Singleton
    @Provides
    @Named("tok")
    fun providetoken():String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}