package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SDG(
    @Json(name = "name")
    val name: String
)
