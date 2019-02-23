package com.riningan.dota2heroes.data.network

import com.squareup.moshi.Json


data class HeroResponse(
        var id: Int,
        var name: String,
        @Json(name = "localized_name") var localizedName: String,
        var img: String,
        var icon: String,
        @Json(name = "pro_win") var pro_win: Int,
        @Json(name = "pro_pick") var pro_pick: Int,
        @Json(name = "hero_id") var hero_id: Int,
        @Json(name = "pro_ban") var pro_ban: Int,
        @Json(name = "1_pick") var pick1: Int,
        @Json(name = "1_win") var win1: Int,
        @Json(name = "2_pick") var pick2: Int,
        @Json(name = "2_win") var win2: Int,
        @Json(name = "3_pick") var pick3: Int,
        @Json(name = "3_win") var win3: Int,
        @Json(name = "4_pick") var pick4: Int,
        @Json(name = "4_win") var win4: Int,
        @Json(name = "5_pick") var pick5: Int,
        @Json(name = "5_win") var win5: Int,
        @Json(name = "6_pick") var pick6: Int,
        @Json(name = "6_win") var win6: Int,
        @Json(name = "7_pick") var pick7: Int,
        @Json(name = "7_win") var win7: Int
)