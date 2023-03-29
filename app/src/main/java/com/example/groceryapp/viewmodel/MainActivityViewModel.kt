package com.example.groceryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.groceryapp.model.data.local.AppRepository
import com.example.groceryapp.utils.BaseViewModel

class MainActivityViewModel(repository: AppRepository) : BaseViewModel() {

    val cartCount: LiveData<Int> = repository.getCartCount()

    private val _showCart = MutableLiveData<Boolean?>()
    val showCart: LiveData<Boolean?> = _showCart

    fun showCart(show: Boolean){
        _showCart.value = show
    }
}