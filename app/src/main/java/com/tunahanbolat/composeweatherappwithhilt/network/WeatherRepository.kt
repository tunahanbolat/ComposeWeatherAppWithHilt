package com.tunahanbolat.composeweatherappwithhilt.network

import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getWeather(city: String, lang: String): Response<WeatherResponse> {
        return weatherService.getWeather("4bb85cc0fb8b4cb8b4f101724241504",city, lang)
    }
}