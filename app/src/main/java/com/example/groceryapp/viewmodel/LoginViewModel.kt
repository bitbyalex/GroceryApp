package com.example.groceryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groceryapp.model.data.remote.LoginResponse
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    private val apiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)

    private val compositeDisposable = CompositeDisposable()

    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    fun login(email: String, password: String) {
        val disposable = apiService.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val loginResponse = LoginResponse(it.token, it.user)
                _loginResult.postValue(loginResponse)
            }, {
                Log.e("LoginViewModel", "Login failed: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}

