package com.tunahanbolat.composeweatherappwithhilt.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    val location: Location,
    val current: Current
)

data class Current (
    val lastUpdatedEpoch: Long,
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Long,
    val tempF: Long,
    val isDay: Long,
    val condition: Condition,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Long,
    val windDir: String,
    val pressureMB: Long,
    val pressureIn: Double,
    val precipMm: Long,
    val precipIn: Long,
    val humidity: Long,
    val cloud: Long,
    val feelslikeC: Long,
    val feelslikeF: Long,
    val visKM: Long,
    val visMiles: Long,
    val uv: Long,
    val gustMph: Double,
    val gustKph: Double
)

data class Condition (
    val text: String,
    val icon: String,
    val code: Long
)

data class Location (
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tzID: String,
    val localtimeEpoch: Long,
    val localtime: String
)