package com.juliocezar.gw2achievguide.common.data.remote.model

import com.google.gson.annotations.SerializedName


data class AccountDTO(
    val id: String,
    val name: String,
    val access: List<String>,
    @SerializedName("fractal_level") val fractalLevel: Int,
    val world: Int, //verificar em /v2/worlds
    val created: String,
    @SerializedName("wvw_rank") val wvwRank: Number
)

data class WorldDTO(
    val id: Int,
    val name: String
)


// /v2/achievements e /v2/account/achievements  /v2/account