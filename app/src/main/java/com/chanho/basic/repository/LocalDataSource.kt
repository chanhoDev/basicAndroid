package com.chanho.basic.repository

import com.chanho.basic.room.MovieFavoriteEntity
import com.chanho.basic.room.MovieSearchDao
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSource(
    private val movieSearchDao: MovieSearchDao
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

//    override fun onMovieFavoiteInsertCall(movieFavoriteEntity: MovieFavoriteEntity): Completable {
//    }
//
//    override fun onMovieFavoiteGetCall(): Single<List<MovieFavoriteEntity>> {
//    }
//
//    override fun onMovieFavoiteDeleteCall(): Completable {
//    }
//
//    override fun onMovieFavoiteUpdate(movieFavoriteEntity: MovieFavoriteEntity): Completable {
//    }

}