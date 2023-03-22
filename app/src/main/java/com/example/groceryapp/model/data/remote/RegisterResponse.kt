package com.example.groceryapp.model.data.remote

data class RegisterResponse(
    val `data` : Data,
    val error : Boolean,
    val message: String
)
