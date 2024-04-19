package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.network.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private var _weatherDataList: MutableState<List<WeatherResponse>> = mutableStateOf(emptyList())
    var weatherDataList: State<List<WeatherResponse>> = _weatherDataList

    var error = mutableStateOf<String?>(null)

    init {
        fetchWeather("tr")
    }

    fun fetchWeather(lang: String) {
        viewModelScope.launch {
            val newResponse = mutableListOf<WeatherResponse>()
            for (i in 0..21){
                try {

                    val response = weatherRepository.getWeather(sehirList[i], lang)

                    if (response.isSuccessful) {
                        newResponse.add(response.body()!!)
                    } else {
                        error.value = "Error fetching weather data: ${response.message()}"
                    }
                } catch (e: Exception) {
                    error.value = "Error fetching weather data: ${e.message}"
                }
            }
            _weatherDataList.value = newResponse.toList()
        }
    }

    private val sehirList = arrayListOf(
        "Corum","Adana", "Ankara", "Antalya", "Bursa",
        "Denizli", "Erzurum", "Eskişehir", "Gaziantep", "Istanbul",
        "Izmir","Kastamonu", "Kayseri", "Kocaeli", "Konya",
        "Manisa","Mersin", "Sakarya", "Samsun", "Trabzon",
        "Yozgat","Zonguldak"
    )
}