package com.juliocezar.gw2achievguide.di

import com.juliocezar.gw2achievguide.common.data.remote.RetrofitClient
import com.juliocezar.gw2achievguide.common.data.remote.GW2ApiService
import com.juliocezar.gw2achievguide.common.data.remote.RetrofitFactory
import com.juliocezar.gw2achievguide.viewmodel.WelcomeViewModel
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
    fun provideRetrofitFactory(): RetrofitFactory {
        return RetrofitFactory()
    }
}