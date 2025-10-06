package se.linerotech.module202.lecture.countryDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.linerotech.module202.lecture.api.CountriesDTO
import se.linerotech.module202.lecture.api.CountriesRetrofitClient
import se.linerotech.module202.lecture.api.CountryDTO
import se.linerotech.module202.lecture.common.CountryDetail
import java.text.NumberFormat
import java.util.Locale

class CountryDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow<CountryDetailState>(CountryDetailState.Loading)
    val state: StateFlow<CountryDetailState> = _state

    fun loadDummyData(countryName: String) {
        if (countryName.isBlank()) {
            _state.value = CountryDetailState.Failure("No country selected.")
            return
        }

        _state.value = CountryDetailState.Loading

        viewModelScope.launch {
            val call = CountriesRetrofitClient.instance.getCountries()
            call.enqueue(CountryCallback(countryName))
        }
    }

    /**
     * Separate inner class instead of returning an anonymous Callback,
     * so Detekt indentation rules stop complaining.
     */
    private inner class CountryCallback(
        private val countryName: String
    ) : Callback<CountriesDTO> {

        override fun onResponse(
            call: Call<CountriesDTO>,
            response: Response<CountriesDTO>
        ) {
            if (!response.isSuccessful) {
                _state.value = CountryDetailState.Failure("HTTP ${response.code()}")
                return
            }

            val match = response.body()?.countries?.firstOrNull {
                it.name.equals(countryName, ignoreCase = true) ||
                    (it.officialName?.equals(countryName, ignoreCase = true) == true)
            }

            _state.value = when (match) {
                null -> CountryDetailState.Failure("No data for $countryName")
                else -> CountryDetailState.Success(match.toUi())
            }
        }

        override fun onFailure(
            call: Call<CountriesDTO>,
            t: Throwable
        ) {
            Log.e("CountryDetailVM", "Network error", t)
            _state.value = CountryDetailState.Failure(t.message ?: "Network error")
        }
    }

    // ---------- helpers ----------

    private val numberFormat: NumberFormat by lazy {
        NumberFormat.getIntegerInstance(Locale.getDefault())
    }

    private fun formatInt(n: Long?): String =
        n?.let { numberFormat.format(it).replace(',', ' ') } ?: "—"

    private fun formatArea(area: Double?): String =
        area?.let { numberFormat.format(it.toLong()).replace(',', ' ') } ?: "—"

    private fun firstNonBlank(vararg s: String?): String? =
        s.firstOrNull { !it.isNullOrBlank() }

    private fun CountryDTO.regionLine(): String =
        firstNonBlank(subregion, region) ?: "—"

    private fun CountryDTO.bigFlagUrl(): String =
        flag.large ?: flag.medium ?: flag.small ?: ""

    private fun CountryDTO.capitalText(): String = capital ?: "—"

    private fun CountryDTO.toUi(): CountryDetail = CountryDetail(
        name = officialName ?: name,
        region = regionLine(),
        flagUrl = bigFlagUrl(),
        population = formatInt(population),
        area = formatArea(area),
        capital = capitalText(),
        callingCode = callingCode ?: "—",
        topLevelDomain = topLevelDomain?.firstOrNull() ?: "—"
    )

    companion object {
        fun factory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CountryDetailViewModel() as T
            }
        }
    }
}
