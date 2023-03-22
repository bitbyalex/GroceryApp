package com.example.groceryapp.model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val _id: String,
        val quantity: Int,
        val description: String,
        val status: Boolean,
        val catId: Int,
        val subId: Int,
        val productName: String,
        val unit: String,
        val price: Float
)