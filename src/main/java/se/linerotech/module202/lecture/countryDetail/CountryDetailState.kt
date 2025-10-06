package se.linerotech.module202.lecture.countryDetail

import se.linerotech.module202.lecture.common.CountryDetail

sealed class CountryDetailState {
    object Loading : CountryDetailState()
    data class Success(val detail: CountryDetail) : CountryDetailState()
    data class Failure(val message: String) : CountryDetailState()
}
