package com.project1.cookbook.dto

data class SearchRecipeData(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)