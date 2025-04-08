package com.juliocezar.gw2achievguide.di

import com.juliocezar.gw2achievguide.common.data.remote.RetrofitClient
import com.juliocezar.gw2achievguide.common.data.remote.GW2ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(): String {
        return "apiKey"
    }

    @Provides
    @Singleton
    fun provideRetrofit(@Named("apiKey") apiKey: String): Retrofit {
        return RetrofitClient.getRetrofitInstance(apiKey)
    }

    @Provides
    @Singleton
    fun provideGW2ApiService(retrofit: Retrofit): GW2ApiService {
        return retrofit.create(GW2ApiService::class.java)
    }
}