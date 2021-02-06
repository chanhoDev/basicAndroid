package com.chanho.basic.repository

import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.model.MovieResModel
import com.chanho.basic.retrofit.RetrofitNaverService
import io.reactivex.Single
import retrofit2.Response

class RemoteDataSource(
    private val retrofitNaverService: RetrofitNaverService
):RemoteDataSourceImpl {
    override fun onNaverMovieListCall(reqModel: MovieReqModel): Single<Response<MovieResModel>> {
        return retrofitNaverService.getMovieList(
            reqModel.query,
            reqModel.display,
            reqModel.start,
            reqModel.genre,
            reqModel.country,
            reqModel.yearfrom,
            reqModel.yearto
        )
    }

}