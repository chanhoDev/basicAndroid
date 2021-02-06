package com.chanho.basic.repository

import com.chanho.basic.room.MovieSearchDao
import com.chanho.basic.room.MovieSearchEntity
import io.reactivex.Completable

class LocalDataSource(
    private val movieSearchDao: MovieSearchDao
):LocalDataSourceImpl {

    override fun onMovieSearchInsertCall(reqModel: MovieSearchEntity): Completable {
        return movieSearchDao.insert(reqModel)
    }

    override fun onMovieSearchHistoryCall(): List<MovieSearchEntity> {
        return movieSearchDao.get()
    }

}