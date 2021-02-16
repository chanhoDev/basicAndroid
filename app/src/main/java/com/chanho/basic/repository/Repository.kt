package com.chanho.basic.repository

import android.annotation.SuppressLint
import android.util.Log
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import com.chanho.basic.room.MovieFavoriteEntity
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Completable
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
                            if (it.size > 0) {
                                updateSearch(MovieSearchEntity(0, reqModel.query))
                            } else {

                                setMovieSearch(reqModel.query)
                            }
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

    override fun updateSearch(entity: MovieSearchEntity): Disposable {
        return localDataSource.onMovieSearchUpdate(entity)
            .subscribeOn(Schedulers.io()).subscribe()
    }

    //즐겨찾기 리스트
    override fun getMovieFavoriteList(): Single<List<MovieFavoriteEntity>> {
        return localDataSource.onMovieFavoiteGetCall()
            .subscribeOn(Schedulers.io())
    }

    // 즐겨찾기 추가시 기존에 해당 컬럼이 존재하는지 확인
    @SuppressLint("CheckResult")
    override fun setMovieFavorite(entity: MovieFavoriteEntity): Completable {
        return localDataSource.onMovieFavoiteInsertCall(entity)
            .subscribeOn(Schedulers.io())
    }

    //즐겨찾기 삭제
    override fun deleteMovieFavoriteCall(userId: Int): Disposable {
        return localDataSource.onMovieFavoiteDeleteCall(userId)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    //즐겨찾기 업데이트
    override fun updateMovieFavorite(entity: MovieFavoriteEntity): Disposable {
        return localDataSource.onMovieFavoiteUpdate(entity)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun isExistMovieFavorite(title: String, director: String): Single<Int> {
        return localDataSource.isExistMovieFavorite(title,director)
            .subscribeOn(Schedulers.io())
    }
}
