package com.chanho.basic.repository

import android.util.Log
import com.chanho.basic.model.StoreInfo
import com.chanho.basic.retrofit.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class Repository
constructor(
    private val retrofitService: RetrofitService
) {
    fun getFetchStoreInfo(
        lat: Double,
        lng: Double):
            Single<Response<StoreInfo>> {
        return retrofitService.fetchStoreInfo(lat, lng)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if (it.isSuccessful) {
                    Log.e("getFetchStoreInfoSucces", it.body().toString())
                } else {
                    Log.e("getFetchStoreInfoFAil ", it.body().toString())
                }
                it
            }
    }
}
