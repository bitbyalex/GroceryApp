package com.example.groceryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groceryapp.model.data.remote.LoginResponse
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.RetrofitBuilder
import com.example.groceryapp.model.data.local.AppRepository
import com.example.groceryapp.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: AppRepository) : BaseViewModel() {

    private val _loginResult = MutableLiveData<Boolean?>()
    val loginResult: LiveData<Boolean?> = _loginResult

    fun checkIfAlreadyLoggedIn(){
        if(repository.isLoggedIn()){
            _loginResult.value = true
        }
    }

    fun login(email: String, password: String) {
        val disposable = repository.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loginResult.value = it!=null
            }, {
                Log.e("LoginViewModel", "Login failed: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

}

