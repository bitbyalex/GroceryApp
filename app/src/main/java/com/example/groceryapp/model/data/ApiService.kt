package com.example.groceryapp.model.data

import com.example.groceryapp.model.data.remote.*
import com.example.groceryapp.model.Constants
import com.example.groceryapp.model.Constants.CATEGORY_END_POINT
import com.example.groceryapp.model.data.remote.ProductsBySubIDResponse
import com.example.groceryapp.model.data.remote.SubCatResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Single<LoginResponse>

    @POST("auth/register")
    fun register(
        @Body registerData: RegisterData
    ) : Single<RegisterResponse>

    @GET(CATEGORY_END_POINT)
    fun getCategories() : Single<CategoryResponse>

    @GET("${Constants.SEARCH_END_POINT}{name}")
    fun searchProduct(@Path("name") search: String) : Single<SearchResponse>

    @GET("${Constants.SUBCATEGORY_END_POINT}{subId}")
    fun getSubcategories(@Path("subId") subId: String) : Single<SubCatResponse>

    @GET("${Constants.PRODUCTS_BY_SUB_ID_END_POINT}{subId}")
    fun getProductsBySubId(@Path("subId") subId: String) : Single<ProductsBySubIDResponse>
}