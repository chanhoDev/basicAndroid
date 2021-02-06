package com.chanho.basic.repository

import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Completable

interface LocalDataSourceImpl {
    //MovieSearch Insert
    fun onMovieSearchInsertCall(reqModel:MovieSearchEntity):Completable

    //MovisSearch get
    fun onMovieSearchHistoryCall():List<MovieSearchEntity>
}