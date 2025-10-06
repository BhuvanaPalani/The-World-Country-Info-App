package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatLng(
    @Json(name = "capital")
    val capital: List<Double>,
    @Json(name = "country")
    val country: List<Double>
)
