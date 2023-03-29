package com.example.groceryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.remote.Product
import com.example.groceryapp.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val apiService: ApiService): BaseViewModel() {

    private val _searchResults = MutableLiveData<List<Product>>()
    val searchResults : LiveData<List<Product>> = _searchResults


    fun searchProduct(item: String){
        compositeDisposable.add(apiService.searchProduct(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchResults.value = it.data
            }, {
                Log.i("search", "Search Failed")
            })
        )
    }

}