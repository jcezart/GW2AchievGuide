package com.juliocezar.gw2achievguide.common.data.remote.model

import com.google.gson.annotations.SerializedName

data class AchievCategoryDTO (
    val id: Int,
    val name: String,
    val description: String,
    val icon: String,
    val achievements: List<AchievementDTO>,
    @SerializedName("required_access") val requiredAccess: RequiredAccessDTO?

)

data class RequiredAccessDTO(
    val product: String,
    val condition: String
)