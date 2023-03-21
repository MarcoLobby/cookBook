package com.project1.cookbook

import com.project1.cookbook.dto.SearchRecipeData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("/recipes/complexSearch")

    suspend fun extractRecipe(
        @Query("type") type: String
    ): SearchRecipeData
}