package com.chanho.basic.repository

import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import retrofit2.Response

interface RepositoryImpl {
    fun getNaverMovieList(reqModel: MovieReqModel): Single<Response<MovieResModel>>
    fun setMovieSearch(query: String): Disposable
    fun getSearchList(): Single<List<MovieSearchEntity>>
    fun deleteAllSearch(query: String): Disposable
    fun updateSearch(entity:MovieSearchEntity):Disposable
}
