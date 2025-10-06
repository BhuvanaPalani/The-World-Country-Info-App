package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ita(
    @Json(name = "common")
    val common: String,
    @Json(name = "official")
    val official: String
)
