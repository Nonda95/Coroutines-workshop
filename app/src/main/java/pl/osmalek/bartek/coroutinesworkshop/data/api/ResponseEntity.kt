package pl.osmalek.bartek.coroutinesworkshop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ResponseEntity<T>(
    @Json(name = "results")
    val results: List<T>
)
