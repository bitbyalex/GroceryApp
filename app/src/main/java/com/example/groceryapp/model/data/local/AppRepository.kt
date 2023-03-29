package com.example.groceryapp.model.data.local

import androidx.lifecycle.LiveData
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.remote.LoginData
import io.reactivex.Single

// A repository class abstracts access to multiple data sources.
// The repository is not part of the Architecture Components libraries,
// but is a suggested best practice for code separation and architecture.
class AppRepository(
    private val apiService: ApiService,
    private val cartItemDao: CartItemDao,
    private val appPreference: AppPreference,
) {

    fun login(email: String, password: String) =
        apiService.login(LoginData(email,password))
            .map {
                appPreference.saveLoginInfo(it)
                it
            }

    fun isLoggedIn() = appPreference.getLoginInfo() != null

    fun getProductForSubCategory(subCategoryId: String): Single<List<Product>> {
        return Single.create {emitter ->
            val response =
                apiService.getProductsBySubId(subCategoryId).blockingGet()
            if(response != null) {
                val cartItems = HashMap<String, Product>().apply {
                    cartItemDao.getAllCartItems().forEach{product ->
                        put(product.productId,product)
                    }
                }
                response.data.map {
                    val quantity = cartItems[it._id]?.quantity ?:0
                    mapRemoteProductToLocal(it,quantity)
                }.also {
                    emitter.onSuccess(it)
                }
            } else {
                emitter.onError(Exception("Failed to load products"))
            }
        }
    }

    fun getCartCount() = cartItemDao.getCartCount()

    suspend fun clearCart(){
        cartItemDao.deleteAllItem()
    }

    suspend fun updateCart(cartItem: Product) : Product {
        if(cartItem.quantity < 1) {
            cartItemDao.deleteItem(cartItem.productId)
        } else {
            cartItemDao.addUpdateProduct(
                cartItem
            )
        }
        return cartItem
    }
    private fun mapRemoteProductToLocal(
        product: com.example.groceryapp.model.data.remote.Product,
        quantity : Int
    ) = product.run {
        Product(
            _id, quantity, description, image, status, catId, subId, productName, unit, price
        )
    }

}
