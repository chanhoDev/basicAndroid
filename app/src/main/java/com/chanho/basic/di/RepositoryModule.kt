package com.chanho.basic.di

import com.chanho.basic.repository.Repository
import com.chanho.basic.retrofit.RetrofitNaverService
import com.chanho.basic.retrofit.RetrofitService
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
        retrofitService: RetrofitService,
        retrofitNaverService: RetrofitNaverService
    ):Repository{
        return Repository(retrofitService,retrofitNaverService)
    }
}