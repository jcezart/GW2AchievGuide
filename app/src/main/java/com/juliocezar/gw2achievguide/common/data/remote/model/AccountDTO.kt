package com.juliocezar.gw2achievguide.common.data.remote.model

import com.google.gson.annotations.SerializedName


data class AccountDTO(
    val id: String,
    val name: String,
    val access: List<String>,
    @SerializedName("fractal_level") val fractalLevel: Int
)


// /v2/achievements e /v2/account/achievements  /v2/account