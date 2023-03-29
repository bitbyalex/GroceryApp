package com.example.groceryapp.model.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Query("SELECT * FROM cart_table WHERE productId = :productId")
    fun getProductById(productId: Int) : LiveData<Product>

    @Query("SELECT * FROM cart_table")
    fun getAllProducts() : LiveData<List<Product>>

    @Query("SELECT * FROM cart_table")
    fun getAllCartItems() : List<Product>

    @Query("SELECT SUM(quantity) FROM cart_table")
    fun getCartCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpdateProduct(product: Product)

    @Query("DELETE FROM cart_table WHERE productId = :productId")
    suspend fun deleteItem(productId: String) : Int

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllItem():Int

}