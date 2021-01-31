package com.chanho.basic.retrofit

import com.chanho.basic.model.MovieResModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNaverService {

    @GET("movie.json")
    fun getMovieList(
        @Query("query")  query:String,
        @Query("display") display:String?,
        @Query("start") start:String?,
        @Query("genre") genre:String?,
        @Query("country") country:String?,
        @Query("yearfrom") yearfrom:String?,
        @Query("yearto") yearto:String?
    ): Single<Response<MovieResModel>>
}