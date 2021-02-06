package com.chanho.basic.di

import com.chanho.basic.repository.LocalDataSourceImpl
import com.chanho.basic.repository.RemoteDataSourceImpl
import com.chanho.basic.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSourceImpl,
        remoteDataSource: RemoteDataSourceImpl
    ):Repository{
        return Repository(localDataSource,remoteDataSource)
    }
}