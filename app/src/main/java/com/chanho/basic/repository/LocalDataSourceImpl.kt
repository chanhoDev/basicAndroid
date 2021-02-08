package com.chanho.basic.repository

import com.chanho.basic.room.MovieFavoriteEntity
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSourceImpl {
    //MovieSearch Insert
    fun onMovieSearchInsertCall(reqModel: MovieSearchEntity): Completable

    //MovisSearch get
    fun onMovieSearchGetCall(): Single<List<MovieSearchEntity>>

    //MovieSearch deleteAll
    fun onMovieSearchDeleteAllCall(): Completable

    //MovieSearch Update
    fun onMovieSearchUpdate(movieSearchEntity: MovieSearchEntity): Completable

    //MovieSearch Insert
    fun onMovieFavoiteInsertCall(movieFavoriteEntity: MovieFavoriteEntity): Completable

    //MovisSearch get
    fun onMovieFavoiteGetCall(): Single<List<MovieFavoriteEntity>>

    //MovieSearch deleteAll
    fun onMovieFavoiteDeleteCall(): Completable

    //MovieSearch Update
    fun onMovieFavoiteUpdate(movieFavoriteEntity: MovieFavoriteEntity): Completable

}