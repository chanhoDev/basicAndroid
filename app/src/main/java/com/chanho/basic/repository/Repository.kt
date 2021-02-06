package com.chanho.basic.repository

import android.annotation.SuppressLint
import android.util.Log
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Repository
constructor(
    private val localDataSource: LocalDataSourceImpl,
    private val remoteDataSource: RemoteDataSourceImpl
) {

    @SuppressLint("CheckResult")
    fun getNaverMovieList(
        reqModel: MovieReqModel
    ):
            Single<Response<MovieResModel>> {
        return remoteDataSource.onNaverMovieListCall(reqModel)
            .subscribeOn(Schedulers.io())
            .map {
                if (it.isSuccessful) {
                    if(reqModel.start=="1"){
                        localDataSource.onMovieSearchInsertCall(MovieSearchEntity(reqModel.query))
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                    }
                } else {
                    Log.e("getNaverMovieListFAil ", it.body().toString())
                }
                it
            }
    }
    fun getSearchList(
    ): List<MovieSearchEntity> {
        return localDataSource.onMovieSearchHistoryCall()
    }
}
