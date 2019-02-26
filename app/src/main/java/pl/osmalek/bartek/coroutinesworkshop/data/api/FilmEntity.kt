package pl.osmalek.bartek.coroutinesworkshop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmEntity(
    @Json(name = "title")
    val name: String,
    @Json(name = "episode_id")
    val episodeId: Int,
    @Json(name = "characters")
    val charactersUrls: List<String>
)
