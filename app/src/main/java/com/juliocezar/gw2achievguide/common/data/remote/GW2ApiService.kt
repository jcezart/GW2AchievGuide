package com.juliocezar.gw2achievguide.common.data.remote

import com.juliocezar.gw2achievguide.common.data.remote.model.AccountDTO
import com.juliocezar.gw2achievguide.common.data.remote.model.AchievCategoryDTO
import com.juliocezar.gw2achievguide.common.data.remote.model.AchievementDTO
import com.juliocezar.gw2achievguide.common.data.remote.model.WorldDTO
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GW2ApiService {

    @GET("account")
    suspend fun getAccount(): AccountDTO

    @GET("worlds")
    suspend fun getWorldById(
        @Query("ids") worldId: Int): List<WorldDTO>

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