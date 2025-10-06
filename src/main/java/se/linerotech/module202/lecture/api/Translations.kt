package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Translations(
    @Json(name = "ara")
    val ara: String,
    @Json(name = "ces")
    val ces: String,
    @Json(name = "cym")
    val cym: String,
    @Json(name = "deu")
    val deu: String,
    @Json(name = "est")
    val est: String,
    @Json(name = "fin")
    val fin: String,
    @Json(name = "fra")
    val fra: String,
    @Json(name = "hrv")
    val hrv: String,
    @Json(name = "hun")
    val hun: String,
    @Json(name = "ita")
    val ita: String,
    @Json(name = "jpn")
    val jpn: String,
    @Json(name = "kor")
    val kor: String,
    @Json(name = "nld")
    val nld: String,
    @Json(name = "per")
    val per: String,
    @Json(name = "pol")
    val pol: String,
    @Json(name = "por")
    val por: String,
    @Json(name = "rus")
    val rus: String,
    @Json(name = "slk")
    val slk: String,
    @Json(name = "spa")
    val spa: String,
    @Json(name = "swe")
    val swe: String,
    @Json(name = "urd")
    val urd: String,
    @Json(name = "zho")
    val zho: String
)
