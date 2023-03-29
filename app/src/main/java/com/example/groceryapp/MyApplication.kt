package com.example.groceryapp

import android.app.Application
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.RetrofitBuilder
import com.example.groceryapp.model.data.local.AppDatabase
import com.example.groceryapp.model.data.local.AppPreference
import com.example.groceryapp.model.data.local.AppRepository

class MyApplication: Application() {

    lateinit var repository: AppRepository
    lateinit var api : ApiService

    override fun onCreate() {
        super.onCreate()
        val appDatabase = AppDatabase.getInstance(applicationContext)
        api = RetrofitBuilder.getRetrofit()
            .create(ApiService::class.java)
        repository = AppRepository(
            api, appDatabase.productDao(), AppPreference(applicationContext)
        )
    }
}