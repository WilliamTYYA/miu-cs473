package com.miu.fsapp.core.network

import com.miu.fsapp.feature.userprofile.data.remote.api.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private val retrofit: Retrofit by lazy {
//    private val retrofit: Lazy<Retrofit> = lazy {
        Retrofit.Builder()
            .baseUrl("http://10.200.3.218:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
//    val apiService: UserApiService = retrofit.value.create(UserApiService::class.java)
}