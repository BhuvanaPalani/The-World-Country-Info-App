package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegionalBloc(
    @Json(name = "acronym")
    val acronym: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "otherAcronyms")
    val otherAcronyms: List<String>,
    @Json(name = "otherNames")
    val otherNames: List<String>
)
