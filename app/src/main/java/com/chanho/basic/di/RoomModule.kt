package com.chanho.basic.di

import android.content.Context
import androidx.room.Room
import com.chanho.basic.room.MovieFavoriteDao
import com.chanho.basic.room.MovieFavoriteDatabase
import com.chanho.basic.room.MovieSearchDao
import com.chanho.basic.room.MovieSearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideMovieSearchDb(@ApplicationContext context: Context): MovieSearchDatabase {
        return Room.databaseBuilder(
            context,
            MovieSearchDatabase::class.java,
            MovieSearchDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieSearchDAO(movieSearchDatabase: MovieSearchDatabase):MovieSearchDao{
        return movieSearchDatabase.movieSearchDao()
    }

    @Singleton
    @Provides
    fun provideMovieFavoritehDb(@ApplicationContext context: Context): MovieFavoriteDatabase {
        return Room.databaseBuilder(
            context,
            MovieFavoriteDatabase::class.java,
            MovieFavoriteDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieFavoriteDAO(movieFavoriteDatabase: MovieFavoriteDatabase):MovieFavoriteDao{
        return movieFavoriteDatabase.movieFavoriteDao()
    }

}