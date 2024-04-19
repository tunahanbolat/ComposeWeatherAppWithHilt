package com.tunahanbolat.composeweatherappwithhilt.network

import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey:String,
        @Query("q") city: String,
        @Query("lang") lang: String
    ): Response<WeatherResponse>
}