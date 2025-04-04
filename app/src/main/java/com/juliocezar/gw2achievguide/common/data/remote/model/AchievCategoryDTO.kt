package com.juliocezar.gw2achievguide.common.data.remote.model

import com.google.gson.annotations.SerializedName


data class CategoryAchievementDTO(
    val id: Int
)

data class AchievCategoryDTO (
    val id: Int,
    val name: String,
    val description: String,
    val icon: String,
    val order: Int,
    val achievements: List<CategoryAchievementDTO>


)

