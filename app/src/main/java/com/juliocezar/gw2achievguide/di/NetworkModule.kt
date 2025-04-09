package com.juliocezar.gw2achievguide.di

import com.juliocezar.gw2achievguide.common.data.remote.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitFactory(): RetrofitFactory {
        return RetrofitFactory()
    }
}