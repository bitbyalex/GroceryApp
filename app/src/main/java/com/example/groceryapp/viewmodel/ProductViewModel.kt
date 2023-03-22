package com.example.groceryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.model.data.local.*
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val productDao: ProductDao
    private val cartItemDao: CartItemDao
    private val repository: ProductRepository

    val allProducts: LiveData<List<Product>>

    val allCartItems: LiveData<List<CartItem>>

    init {
        val database = AppDatabase.getInstance(application)
        productDao = database.productDao()
        cartItemDao = database.cartItemDao()
        repository = ProductRepository(productDao, cartItemDao)
        allProducts = repository.allProducts
        allCartItems = repository.allCartItems
    }

    fun getProductById(productId: Int): LiveData<Product> {
        return repository.getProductById(productId)
    }

    fun insertOrUpdateCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            repository.insertOrUpdateCartItem(cartItem)
        }
    }

    fun deleteCartItem(productId: Int) {
        viewModelScope.launch {
            repository.deleteCartItem(productId)
        }
    }

    fun deleteAllCartItems() {
        viewModelScope.launch {
            repository.deleteAllCartItems()
        }
    }
}

