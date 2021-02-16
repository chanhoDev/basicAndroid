package com.chanho.basic.repository

import android.annotation.SuppressLint
import android.util.Log
import com.chanho.basic.room.MovieFavoriteDao
import com.chanho.basic.room.MovieFavoriteEntity
import com.chanho.basic.room.MovieSearchDao
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LocalDataSource(
    private val movieSearchDao: MovieSearchDao,
    private val movieFavoriteDao: MovieFavoriteDao
) : LocalDataSourceImpl {

    override fun onMovieSearchInsertCall(reqModel: MovieSearchEntity): Completable {
        return movieSearchDao.insertMovieSearch(reqModel)
    }

    override fun onMovieSearchGetCall(): Single<List<MovieSearchEntity>> {
        return movieSearchDao.getMovieSearch()
    }

    override fun onMovieSearchDeleteAllCall(): Completable {
        return movieSearchDao.deleteAllMovieSearch()
    }

    override fun onMovieSearchUpdate(movieSearchEntity: MovieSearchEntity): Completable {
        return movieSearchDao.updateMovieSearch(movieSearchEntity)
    }

    override fun onMovieFavoiteInsertCall(movieFavoriteEntity: MovieFavoriteEntity): Completable {
        return movieFavoriteDao.insertMovieFavoite(movieFavoriteEntity)
    }

    override fun onMovieFavoiteGetCall(): Single<List<MovieFavoriteEntity>> {
        return movieFavoriteDao.getMovieFavoite()
    }

    override fun onMovieFavoiteDeleteCall(userId:Int): Completable {
        return movieFavoriteDao.deleteAllMovieFavoite(userId)
    }

    override fun onMovieFavoiteUpdate(movieFavoriteEntity: MovieFavoriteEntity): Completable {
        return movieFavoriteDao.updateMovieFavoite(movieFavoriteEntity)
    }

    @SuppressLint("CheckResult")
    override fun isExistMovieFavorite(title: String, director: String): Single<Int> {
       return movieFavoriteDao.isExisteMovieFavorite(title,director)
    }

}