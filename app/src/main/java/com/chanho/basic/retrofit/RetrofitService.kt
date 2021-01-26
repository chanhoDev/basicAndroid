package com.chanho.basic.retrofit

import com.chanho.basic.model.StoreInfo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("sample.json")
    fun fetchStoreInfo(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): Single<Response<StoreInfo>>
}