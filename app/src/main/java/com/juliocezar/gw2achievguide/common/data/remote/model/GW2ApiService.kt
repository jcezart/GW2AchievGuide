package com.juliocezar.gw2achievguide.common.data.remote.model

import retrofit2.http.GET
import retrofit2.http.Query

interface GW2ApiService {

    @GET("account")
    suspend fun getAccount(): AccountDTO

    @GET("achievements/categories")
    suspend fun getAchievementCategories(
        @Query("ids") ids: String? = null
    ): List<AchievCategoryDTO>

    @GET("achievements")
    suspend fun getAchievements(
        @Query("ids") ids: String
    ): List<AchievementDTO>

    @GET("account/achievements")
    suspend fun getAccountAchievements(): List<AchievementDTO>

}