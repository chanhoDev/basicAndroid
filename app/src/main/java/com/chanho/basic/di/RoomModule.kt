package com.chanho.basic.di

import android.content.Context
import androidx.room.Room
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

}