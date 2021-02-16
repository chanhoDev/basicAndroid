package com.chanho.basic.di

import com.chanho.basic.repository.LocalDataSource
import com.chanho.basic.repository.LocalDataSourceImpl
import com.chanho.basic.repository.RemoteDataSource
import com.chanho.basic.repository.RemoteDataSourceImpl
import com.chanho.basic.retrofit.RetrofitNaverService
import com.chanho.basic.room.MovieFavoriteDao
import com.chanho.basic.room.MovieSearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        retrofitNaverService: RetrofitNaverService
    ): RemoteDataSourceImpl {
        return RemoteDataSource(retrofitNaverService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        movieSearchDao:MovieSearchDao,
        movieFavoriteDao: MovieFavoriteDao
    ):LocalDataSourceImpl{
        return LocalDataSource(movieSearchDao,movieFavoriteDao)
    }
}