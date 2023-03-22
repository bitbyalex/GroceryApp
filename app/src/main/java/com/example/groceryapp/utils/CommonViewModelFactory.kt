package com.example.groceryapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.RetrofitBuilder
import com.example.groceryapp.viewmodel.CategoryViewModel

class CommonViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api = RetrofitBuilder.getRetrofit()
            .create(ApiService::class.java)
        return when(modelClass) {
            CategoryViewModel::class.java -> CategoryViewModel(api) as T
            else -> throw Throwable("Unsupported view model")
        }
    }
}