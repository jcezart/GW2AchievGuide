package com.juliocezar.gw2achievguide.common.data.remote

import javax.inject.Inject
import retrofit2.Retrofit

class RetrofitFactory @Inject constructor() {
    fun createRetrofit(apiKey: String): Retrofit {
        return RetrofitClient.getRetrofitInstance(apiKey)
    }
}