package com.riningan.dota2heroes

import com.riningan.dota2heroes.data.model.Hero
import com.riningan.dota2heroes.data.network.HeroResponse


val HeroResponse1 = HeroResponse(
    1,
    "npc_dota_hero_antimage",
    "Anti-Mage",
    "/apps/dota2/images/heroes/antimage_full.png?",
    "/apps/dota2/images/heroes/antimage_icon.png",
    60,
    134,
    1,
    268,
    24146,
    11628,
    329,
    149,
    10917,
    5151,
    8506,
    4109,
    31322,
    15183,
    2839,
    1339,
    22318,
    10626
)
val HeroResponse2 = HeroResponse(
    2,
    "npc_dota_hero_axe",
    "Axe",
    "/apps/dota2/images/heroes/axe_full.png?",
    "/apps/dota2/images/heroes/axe_icon.png",
    60,
    134,
    2,
    268,
    24146,
    11628,
    329,
    149,
    10917,
    5151,
    8506,
    4109,
    31322,
    15183,
    2839,
    1339,
    22318,
    10626
)

val Hero1 = Hero(
    HeroResponse1.id,
    HeroResponse1.name,
    HeroResponse1.localizedName,
    HeroResponse1.img
)
val Hero2 = Hero(
    HeroResponse2.id,
    HeroResponse2.name,
    HeroResponse2.localizedName,
    HeroResponse2.img
)