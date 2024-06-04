package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.tunahanbolat.composeweatherappwithhilt.R
import com.tunahanbolat.composeweatherappwithhilt.data.Condition
import com.tunahanbolat.composeweatherappwithhilt.data.Current
import com.tunahanbolat.composeweatherappwithhilt.data.Location
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.genova
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Calendar
import kotlin.math.roundToLong

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel, navController: NavController) {
    val weatherData = viewModel.weatherDataList.value

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    val deger = viewModel.loadingState

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(2000)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(modifier = Modifier.pullRefresh(state)) {
        if (deger.value) {
            ShimmerLoadingScreen()
        } else {
            Scaffold(content = {
                LazyColumn(contentPadding = it) {
                    if (!refreshing) {
                        items(items = weatherData) { item ->
                            WeatherRow(
                                weatherResponse = item, navController
                            )
                        }
                    }
                }
            })
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
    LaunchedEffect(Unit) {
        viewModel.fetchWeather(lang = "tr")
    }
}

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun WeatherRow(
    weatherResponse: WeatherResponse, navController: NavController
) {
    val shape = RoundedCornerShape(12.dp)
    val cardColor: Brush

    val gradientEvening = Brush.linearGradient(
        0.0f to colorResource(id = R.color.back_top),
        500.0f to colorResource(id = R.color.background_bottom),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    val gradientMorning = Brush.linearGradient(
        0.0f to colorResource(id = R.color.morning_top),
        500.0f to colorResource(id = R.color.morning_bottom),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    cardColor = if (hour in 6..18) {
        gradientMorning
    } else {
        gradientEvening
    }

    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clip(shape)
            .clickable {
                val weatherJson = Gson().toJson(weatherResponse)
                val encode = URLEncoder
                    .encode(weatherJson, StandardCharsets.UTF_8.toString())
                    .replace("+", "%20")
                navController.navigate("detay/$encode")
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(cardColor)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = weatherResponse.location.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    fontFamily = genova,
                    color = Color.Black,
                )
                Text(
                    text = weatherResponse.current.condition.text,
                    fontSize = 16.sp,
                    fontFamily = genova,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .weight(3f),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.size(84.dp),
                    model = "https:${weatherResponse.current.condition.icon} ",
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    alpha = 0.8F
                )
                Text(
                    text = "${weatherResponse.current.tempC.roundToLong()} °C",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontFamily = genova,
                    color = Color.White
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun WeatherScreenPreview() {
    AppTheme {
        WeatherRow(
            navController = NavController(LocalContext.current),
            weatherResponse = WeatherResponse(
                location = Location(
                    name = "London",
                    region = "Dummy Region",
                    country = "Dummy Country",
                    lat = 0.0,
                    lon = 0.0,
                    tzID = "Dummy/Timezone",
                    localtimeEpoch = 0L,
                    localtime = "2024-04-19 06:51:08"
                ), current = Current(
                    lastUpdatedEpoch = 0L,
                    lastUpdated = "2024-04-19 06:51:08",
                    tempC = 20.0,
                    tempF = 68.0,
                    isDay = 1L,
                    condition = Condition(
                        text = "Sunny",
                        icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                        code = 1000L
                    ),
                    windMph = 10.0,
                    windKph = 16.0,
                    windDegree = 90L,
                    windDir = "E",
                    pressureMB = 1013L,
                    pressureIn = 30.0,
                    precipMm = 0L,
                    precipIn = 0L,
                    humidity = 50L,
                    cloud = 0L,
                    feelslikeC = 20.0,
                    feelslikeF = 68.0,
                    visKM = 10L,
                    visMiles = 6L,
                    uv = 5L,
                    gustMph = 12.0,
                    gustKph = 19.3
                )
            )
        )
    }
}
