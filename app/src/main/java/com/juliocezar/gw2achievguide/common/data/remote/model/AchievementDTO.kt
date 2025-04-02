package com.juliocezar.gw2achievguide.common.data.remote.model

data class AchievementDTO(
    val id: Int,
    val icon: String,
    val name: String,
    val description: String,
    val prerequisites: List<Int>,
    val current: Int,
    val max: Int,
    val done: Boolean
)


