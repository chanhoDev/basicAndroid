package com.chanho.basic.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface MovieSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieSearchEntity:MovieSearchEntity):Completable

    @Query("SELECT * FROM moviesearch")
    fun get(): List<MovieSearchEntity>

    @Query("DELETE FROM movieSearch")
    fun deleteAllMovieSearch()
}