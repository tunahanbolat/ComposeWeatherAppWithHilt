package com.tunahanbolat.composeweatherappwithhilt.ui.screen
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.decode.ImageDecoderDecoder
import com.google.gson.Gson
import com.tunahanbolat.composeweatherappwithhilt.R
import com.tunahanbolat.composeweatherappwithhilt.data.Condition
import com.tunahanbolat.composeweatherappwithhilt.data.Current
import com.tunahanbolat.composeweatherappwithhilt.data.Location
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.network.WeatherRepository
import com.tunahanbolat.composeweatherappwithhilt.network.WeatherService
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.kaushan
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.nio.file.WatchEvent
@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel, navController: NavController) {
    val weatherData = viewModel.weatherDataList.value
    val error = viewModel.error.value
    Scaffold(content = {
        LazyColumn(contentPadding = it) {
            items(items = weatherData) { item ->
                WeatherRow(
                    modifier = Modifier, weatherResponse = item, navController
                )
            }
        }
    })
    LaunchedEffect(Unit) {
        viewModel.fetchWeather(lang = "tr")
    }
}
@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun WeatherRow(
    modifier: Modifier, weatherResponse: WeatherResponse, navController: NavController
) {
    val gradient = Brush.horizontalGradient(
        0.0f to colorResource(id = R.color.background_top),
        1.0f to colorResource(id = R.color.background_bottom2),
        startX = 0.0f,
        endX = 1000.0f
    )
    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clickable {
                val weatherJson = Gson().toJson(weatherResponse)
                val encode = URLEncoder
                    .encode(weatherJson, StandardCharsets.UTF_8.toString())
                    .replace("+", "%20")
                navController.navigate("detay/$encode")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
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
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                )
                Text(
                    text = weatherResponse.current.condition.text,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
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
                    text = "${weatherResponse.current.tempC} °C",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontFamily = kaushan,
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
                    name = "Dummy City",
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
                    tempC = 20L,
                    tempF = 68L,
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
                    feelslikeF = 68L,
                    visKM = 10L,
                    visMiles = 6L,
                    uv = 5L,
                    gustMph = 12.0,
                    gustKph = 19.3
                )
            ), modifier = Modifier
        )
    }
}
