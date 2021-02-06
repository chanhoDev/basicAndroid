package com.chanho.basic.repository

import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import io.reactivex.Single
import retrofit2.Response

interface RemoteDataSourceImpl {
    //NAVER MOVIE LIST
    fun onNaverMovieListCall(reqModel:MovieReqModel): Single<Response<MovieResModel>>

}