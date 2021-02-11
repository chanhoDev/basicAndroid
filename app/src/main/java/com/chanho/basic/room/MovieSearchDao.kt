package com.chanho.basic.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieSearch(movieSearchEntity: MovieSearchEntity): Completable

    @Query("SELECT * FROM movieSearch")
    fun getMovieSearch(): Single<List<MovieSearchEntity>>

    @Query("DELETE FROM movieSearch")
    fun deleteAllMovieSearch(): Completable

    @Update
    fun updateMovieSearch(movieSearchEntity: MovieSearchEntity): Completable
}