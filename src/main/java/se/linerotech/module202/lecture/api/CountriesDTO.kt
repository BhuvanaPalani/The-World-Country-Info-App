// se/linerotech/module202/lecture/api/CountriesDTO.kt
package se.linerotech.module202.lecture.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountriesDTO(
    @Json(name = "countries") val countries: List<CountryDTO> = emptyList()
)

@JsonClass(generateAdapter = true)
data class CountryDTO(
    @Json(name = "name") val name: String = "",
    @Json(name = "official_name") val officialName: String? = null,
    @Json(name = "subregion") val subregion: String? = null,
    @Json(name = "region") val region: String? = null,
    @Json(name = "population") val population: Long? = null,
    @Json(name = "area") val area: Double? = null,
    @Json(name = "capital") val capital: String? = null,
    @Json(name = "callingCode") val callingCode: String? = null,
    @Json(name = "topLevelDomain") val topLevelDomain: List<String>? = null,
    @Json(name = "flag") val flag: FlagDTO = FlagDTO()
)

@JsonClass(generateAdapter = true)
data class FlagDTO(
    @Json(name = "small") val small: String = "",
    @Json(name = "medium") val medium: String = "",
    @Json(name = "large") val large: String = ""
)
