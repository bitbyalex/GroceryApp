package com.example.groceryapp.model.data.remote


data class SearchResponse(
    val count: Int,
    val data: List<Product>,
    val error: Boolean
)