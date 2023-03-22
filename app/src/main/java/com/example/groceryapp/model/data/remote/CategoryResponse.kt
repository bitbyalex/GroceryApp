package com.example.groceryapp.model.data.remote

data class CategoryResponse(
    val count: Int,
    val `data`: List<Data>,
    val error: Boolean
)