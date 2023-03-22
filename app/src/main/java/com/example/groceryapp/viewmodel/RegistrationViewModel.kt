package com.example.groceryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groceryapp.model.data.ApiService
import com.example.groceryapp.model.data.RetrofitBuilder
import com.example.groceryapp.model.data.remote.RegisterData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel() : ViewModel() {

    private val apiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)

    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult

    private val compositeDisposable = CompositeDisposable()

    fun register(firstName: String, email: String, password: String, mobile: String) {
        compositeDisposable.add(
            apiService.register(RegisterData(firstName, email, password, mobile))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        if(response.error){
                            _registrationResult.value = RegistrationResult.Error(response.message)
                        }
                        else{
                            _registrationResult.value = RegistrationResult.Success
                        }
                    },
                    {
                        _registrationResult.value = RegistrationResult.Error(it.message.orEmpty())
                        Log.e("LoginViewModel", "Registration failed: ${it.message}")
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

sealed class RegistrationResult{
    object Success: RegistrationResult()
    class Error(val message: String): RegistrationResult()
    object Loading: RegistrationResult()
}