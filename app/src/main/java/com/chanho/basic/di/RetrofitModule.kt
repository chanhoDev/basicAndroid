package com.chanho.basic.di

import com.chanho.basic.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    val BASE_URL =
        "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/"

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder():OkHttpClient.Builder{
        return OkHttpClient.Builder()
            .addInterceptor{chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(builder :OkHttpClient.Builder) : OkHttpClient {
        return builder
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit:Retrofit.Builder):RetrofitService{
        return retrofit
            .build()
            .create(RetrofitService::class.java)

    }


}