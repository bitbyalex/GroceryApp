package com.example.groceryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.remote.Data
import com.example.groceryapp.model.data.remote.Product
import com.example.groceryapp.utils.BaseViewModel
import com.example.groceryapp.model.data.remote.SubCategory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryViewModel(private val apiService: ApiService) : BaseViewModel()  {

    private val _categoryList = MutableLiveData<List<Data>>()
    val categoryList: LiveData<List<Data>> = _categoryList
    private  val _subCategoryList = MutableLiveData<List<SubCategory>>()
    val subCategoryList : LiveData<List<SubCategory>> = _subCategoryList
    private val _itemsList = MutableLiveData<List<Product>>()
    val itemsList : LiveData<List<Product>> = _itemsList
    var products = MutableLiveData<List<Product>>()

    private val compositeDisposable = CompositeDisposable()

    fun getCategories() {
        compositeDisposable.add(
            apiService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        _categoryList.value = response.data
                    },
                    {
                        Log.e("CategoryViewModel", "Category failed: ${it.message}")
                    })
        )
    }

    fun getSubCategories(categoryId : String) {
        compositeDisposable.add(
            apiService.getSubcategories(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        _subCategoryList.value = response.data
                    },
                    {
                        Log.e("CategoryViewModel", "Subcategory failed: ${it.message}")
                    })
        )
    }

    fun getProductForSubCategory(subCategoryId: String){
        compositeDisposable.add(
            apiService.getProductsBySubId(subCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response ->
                        _itemsList.value = response.data
                    }, {
                        Log.e("CategoryViewModel", "Subcategory failed: ${it.message}")
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}