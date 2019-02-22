package com.riningan.dota2heroes.data.network

import com.squareup.moshi.Json


data class HeroResponse(
        var id: Int,
        var name: String,
        @Json(name = "localized_name") var localizedName: String,
        @Json(name = "primary_attr") var primaryAttr: String,
        @Json(name = "attack_type") var attackType: String,
        var roles: List<String>
)