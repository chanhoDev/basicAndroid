package com.chanho.basic.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieFavoite(movieFavoriteEntity: MovieFavoriteEntity): Completable

    @Query("SELECT COUNT(*) FROM moviefavorite WHERE title=:title and director=:director")
    fun isExisteMovieFavorite(title:String,director:String):Single<Int>

    @Query("SELECT * FROM moviefavorite")
    fun getMovieFavoite(): Single<List<MovieFavoriteEntity>>

    @Query("DELETE FROM moviefavorite WHERE id=:userId")
    fun deleteAllMovieFavoite(userId:Int): Completable

    @Update
    fun updateMovieFavoite(movieFavoriteEntity: MovieFavoriteEntity): Completable
}