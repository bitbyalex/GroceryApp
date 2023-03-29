package com.example.groceryapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.groceryapp.MyApplication
import com.example.groceryapp.view.MainActivity
import com.example.groceryapp.viewmodel.*

class CommonViewModelFactory(private val application: MyApplication):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainActivity::class.java -> MainActivityViewModel(application.repository) as T
            LoginViewModel::class.java -> LoginViewModel(application.repository) as T
            CategoryViewModel::class.java -> CategoryViewModel(application.api) as T
            SearchViewModel::class.java -> SearchViewModel(application.api) as T
            ProductViewModel::class.java -> ProductViewModel(application.repository) as T
            else -> throw Throwable("Unsupported view model")
        }
    }
}