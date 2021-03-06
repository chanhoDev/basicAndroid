package com.chanho.basic.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieFavoriteEntity::class], version = 1,exportSchema = false)
abstract class MovieFavoriteDatabase : RoomDatabase() {
    abstract fun movieFavoriteDao(): MovieFavoriteDao

    companion object {
        val DATABASE_NAME: String = "movie_favorite_db"

    }
}