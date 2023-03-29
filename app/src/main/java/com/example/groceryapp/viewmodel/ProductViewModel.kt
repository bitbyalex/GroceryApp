package com.example.groceryapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.model.data.local.*
import com.example.groceryapp.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: AppRepository) : BaseViewModel() {

    private val _itemsList = MutableLiveData<List<Product>>()
    val itemsList: LiveData<List<Product>> = _itemsList

    fun getProductForSubCategory(subCategoryId : String){
        compositeDisposable.add(
            repository.getProductForSubCategory(subCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        response ->
                        _itemsList.value = response
                    } , {
                        Log.e(
                            "CategoryViewModel",
                            "Subcategory failed : ${it.message}"
                        )
                    }
                )
        )
    }

    fun insertOrUpdateCartItem(cartItem: Product){
        viewModelScope.launch {
            updateItem(repository.updateCart(cartItem))
        }
    }

    private fun updateItem(cartItem: Product) {
        _itemsList.value?.indexOfFirst{ it.productId == cartItem.productId }
            ?.takeIf { it > -1 }?.also {
                _itemsList.value = _itemsList.value?.toMutableList().apply {
                    this?.set(it,cartItem)
                }
            }
    }
}

