package se.linerotech.module202.lecture.api

import retrofit2.Call
import retrofit2.http.GET

interface CountriesEndpoint {

    @GET("countries")
    fun getCountries(): Call<CountriesDTO>
}
