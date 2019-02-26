package pl.osmalek.bartek.coroutinesworkshop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetEntity(
    @Json(name = "name")
    val name: String
)
