package se.linerotech.module202.lecture.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.linerotech.module202.lecture.api.CountriesDTO
import se.linerotech.module202.lecture.api.CountriesRetrofitClient
import se.linerotech.module202.lecture.common.Country

class CountryViewModel : ViewModel() {

    private val _state = MutableStateFlow<List<Country>>(emptyList())
    val state: StateFlow<List<Country>> = _state

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        CountriesRetrofitClient.instance.getCountries()
            .enqueue(object : Callback<CountriesDTO> {
                override fun onResponse(call: Call<CountriesDTO>, resp: Response<CountriesDTO>) {
                    if (!resp.isSuccessful) {
                        _error.value = "HTTP ${resp.code()}"
                        _state.value = emptyList()
                        return
                    }
                    val list = resp.body()?.countries.orEmpty()
                    _state.value =
                        list.map { Country(it.name, it.flag.medium) }.sortedBy { it.name }
                }

                override fun onFailure(call: Call<CountriesDTO>, t: Throwable) {
                    _error.value = t.message ?: "Network error"
                    _state.value = emptyList()
                }
            })
    }

    companion object {
        fun factory(): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CountryViewModel() as T
                }
            }
    }
}
