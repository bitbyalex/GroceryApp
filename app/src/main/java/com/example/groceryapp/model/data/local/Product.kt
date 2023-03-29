package com.example.groceryapp.model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Product(
        @PrimaryKey
        val productId: String,
        var quantity: Int,
        val description: String,
        val image : String,
        val status: Boolean,
        val catId: Int,
        val subId: Int,
        val productName: String,
        val unit: String,
        val price: Float
)