package com.example.groceryapp.model.data.local

import androidx.lifecycle.LiveData

// A repository class abstracts access to multiple data sources.
// The repository is not part of the Architecture Components libraries,
// but is a suggested best practice for code separation and architecture.
class ProductRepository(
    private val productDao: ProductDao,
    private val cartItemDao: CartItemDao
) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    val allCartItems: LiveData<List<CartItem>> = cartItemDao.getAllCartItems()

    fun getProductById(productId: Int): LiveData<Product> {
        return productDao.getProductById(productId)
    }

    suspend fun insertOrUpdateCartItem(cartItem: CartItem) {
        cartItemDao.insertOrUpdateCartItem(cartItem)
    }

    suspend fun deleteCartItem(productId: Int) {
        cartItemDao.deleteCartItem(productId)
    }

    suspend fun deleteAllCartItems() {
        cartItemDao.deleteAllCartItems()
    }
}
