package com.example.groceryapp.model.data.remote

import com.example.groceryapp.model.data.remote.Product


data class ProductsBySubIDResponse(
    val count: Int,
    val `data`: List<Product>,
    val error: Boolean
)