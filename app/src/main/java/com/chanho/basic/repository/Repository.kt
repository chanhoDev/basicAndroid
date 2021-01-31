package com.chanho.basic.repository

import android.util.Log
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import com.chanho.basic.model.StoreInfo
import com.chanho.basic.retrofit.RetrofitNaverService
import com.chanho.basic.retrofit.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Repository
constructor(
    private val retrofitService: RetrofitService,
    private val retrofitNaverService: RetrofitNaverService
) {
    fun getFetchStoreInfo(
        lat: Double,
        lng: Double
    ):
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

    fun getNaverMovieList(
        reqModel: MovieReqModel
    ):
            Single<Response<MovieResModel>> {
        return retrofitNaverService.getMovieList(
            reqModel.query,
            reqModel.display,
            reqModel.start,
            reqModel.genre,
            reqModel.country,
            reqModel.yearfrom,
            reqModel.yearto
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if (it.isSuccessful) {
                    Log.e("getNaverMovieListSucces", it.body().toString())
                } else {
                    Log.e("getNaverMovieListFAil ", it.body().toString())
                }
                it
            }
    }

}
