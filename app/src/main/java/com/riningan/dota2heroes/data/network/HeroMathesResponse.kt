package com.riningan.dota2heroes.data.network

import com.squareup.moshi.Json


data class HeroMathesResponse(
        var match_id: Int,
        var duration: Int,
        @Json(name = "start_time") var startTime: Int,
        @Json(name = "radiant_team_id") var radiantTeamId: Int,
        @Json(name = "radiant_name") var radiantName: String,
        @Json(name = "dire_team_id") var direTeamId: Int,
        @Json(name = "dire_name") var direName: String,
        var leagueid: Int,
        @Json(name = "league_name") var leagueName: String,
        @Json(name = "series_id") var seriesId: Int,
        @Json(name = "series_type") var seriesType: Int,
        @Json(name = "radiant_score") var radiantScore: Int,
        @Json(name = "dire_score") var direScore: Int,
        @Json(name = "radiant_win") var radiantWin: Boolean,
        var radiant: Boolean
)