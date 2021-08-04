package com.example.mvvmrecp.domain.model.network.model

import com.example.mvvmrecp.domain.model.responses.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ReciperetrofitService {
    @GET("search")
    suspend fun search(
        @Header("Authorization") token:String,
        @Query("page") page:Int,
        @Query("query") query:String
    ):RecipeSearchResponse

    @GET("get")
    suspend fun get(
        @Header("Authorization") token:String,
        @Query("id") id:Int
    ):RecipeDTO
}