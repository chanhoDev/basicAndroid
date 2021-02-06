package com.chanho.basic.repository

import android.annotation.SuppressLint
import android.util.Log
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Repository
constructor(
    private val localDataSource: LocalDataSourceImpl,
    private val remoteDataSource: RemoteDataSourceImpl
) : RepositoryImpl {

    @SuppressLint("CheckResult")
    override fun getNaverMovieList(
        reqModel: MovieReqModel
    ): Single<Response<MovieResModel>> {
        return remoteDataSource.onNaverMovieListCall(reqModel)
            .subscribeOn(Schedulers.io())
            .map {
                if (it.isSuccessful) {
                    if (reqModel.start == "1") {
                        getSearchList().subscribe({
                            deleteAllSearch(reqModel.query)
                            Log.e("resultNaverList", it.size.toString())
                        }, {
                            Log.e("searchlisterror", it.toString())
                        })
                    }
                } else {
                    Log.e("getNaverMovieListFAil ", it.body().toString())
                }
                it
            }
    }

    override fun setMovieSearch(
        query: String
    ): Disposable {
        return localDataSource.onMovieSearchInsertCall(MovieSearchEntity(query))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }


    @SuppressLint("CheckResult")
    override fun getSearchList(
    ): Single<List<MovieSearchEntity>> {
        return localDataSource.onMovieSearchGetCall()
            .subscribeOn(Schedulers.io())
    }


    override fun deleteAllSearch(query: String): Disposable {
        return localDataSource.onMovieSearchDeleteAllCall()
            .subscribeOn(Schedulers.io()).subscribe({
                setMovieSearch(query)
            }, {
                Log.e("error_deleteAll", it.toString())
            })
    }


}
