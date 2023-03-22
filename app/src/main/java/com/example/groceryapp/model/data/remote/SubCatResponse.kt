package com.example.groceryapp.model.data.remote

import com.example.groceryapp.model.data.remote.SubCategory


data class SubCatResponse(
    val count: Int,
    val data: List<SubCategory>,
    val error: Boolean
)