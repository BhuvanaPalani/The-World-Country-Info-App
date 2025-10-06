package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "alpha2Code")
    val alpha2Code: String,
    @Json(name = "alpha3Code")
    val alpha3Code: String,
    @Json(name = "altSpellings")
    val altSpellings: List<String>,
    @Json(name = "area")
    val area: Double,
    @Json(name = "borders")
    val borders: List<String>,
    @Json(name = "callingCode")
    val callingCode: String,
    @Json(name = "capital")
    val capital: String,
    @Json(name = "cioc")
    val cioc: String,
    @Json(name = "currencies")
    val currencies: Currencies,
    @Json(name = "demonyms")
    val demonyms: Demonyms,
    @Json(name = "flag")
    val flag: Flag,
    @Json(name = "gini")
    val gini: Gini,
    @Json(name = "languages")
    val languages: Languages,
    @Json(name = "latLng")
    val latLng: LatLng,
    @Json(name = "name")
    val name: String,
    @Json(name = "nativeNames")
    val nativeNames: NativeNames,
    @Json(name = "numericCode")
    val numericCode: String,
    @Json(name = "official_name")
    val officialName: String,
    @Json(name = "population")
    val population: Int,
    @Json(name = "region")
    val region: String,
    @Json(name = "regionalBlocs")
    val regionalBlocs: List<RegionalBloc>,
    @Json(name = "subregion")
    val subregion: String,
    @Json(name = "timezones")
    val timezones: List<String>,
    @Json(name = "topLevelDomain")
    val topLevelDomain: List<String>,
    @Json(name = "translations")
    val translations: Translations
)
