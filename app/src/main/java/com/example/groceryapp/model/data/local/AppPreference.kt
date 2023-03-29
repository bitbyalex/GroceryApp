package com.example.groceryapp.model.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.groceryapp.model.data.remote.LoginResponse
import com.google.gson.Gson

class AppPreference(content: Context) {

    private val preference : SharedPreferences

    init {
        preference = content.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    fun saveLoginInfo(response: LoginResponse) {
        preference.edit().apply {
            putString(LOGIN_INFO_KEY, Gson().toJson(response))
        }.apply()
    }

    fun getLoginInfo():LoginResponse? {
        return Gson().fromJson(
            preference.getString(LOGIN_INFO_KEY,null),
            LoginResponse::class.java
        )
    }

    fun clearInfo(){
        preference.edit().clear().apply()
    }

    companion object {
        const val LOGIN_INFO_KEY = "LOGIN_INFO_KEY"
    }
}