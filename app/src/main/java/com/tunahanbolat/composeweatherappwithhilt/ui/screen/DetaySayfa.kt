package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.provider.Contacts.Intents.UI
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tunahanbolat.composeweatherappwithhilt.R
import com.tunahanbolat.composeweatherappwithhilt.data.Condition
import com.tunahanbolat.composeweatherappwithhilt.data.Current
import com.tunahanbolat.composeweatherappwithhilt.data.Location
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.josefinsans
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.kaushan
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(navController: NavController, weatherResponse: WeatherResponse) {
    val gradient = Brush.linearGradient(
        0.0f to Color.Magenta,
        500.0f to Color.Cyan,
        start = Offset.Zero,
        end = Offset.Infinite
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DetailCityTitle(weatherResponse)
        DetailPageIcon(weatherResponse)
        DetailTemperature(weatherResponse)
        DetailSubPageDouble()
        DetailSubPageCompDouble(weatherResponse)
        DetailSubPageCompSecondComp(weatherResponse)
    }
}

@Composable
fun DetailCityTitle(weatherResponse: WeatherResponse) {
    Column() {
        Text(
            text = weatherResponse.location.name,
            fontSize = 32.sp,
            fontFamily = kaushan,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
@Composable
fun DetailPageIcon(weatherResponse: WeatherResponse) {
    Column() {
        AsyncImage(
            model = "https:${weatherResponse.current.condition.icon} ",
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
    }
}

@Composable
fun DetailTemperature(weatherResponse: WeatherResponse) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "${weatherResponse.current.tempC}°C",
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = kaushan,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = weatherResponse.current.condition.text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontFamily = kaushan,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun DetailSubPageDouble() {
    Row(modifier = Modifier.padding(top = 30.dp)) {
        DetailSubPageSun(
            modifier = Modifier.weight(1f),
            "Gündoğumu",
            "05.36",
            R.drawable.sunrise,
            size = 64
        )
        DetailSubPageSun(
            modifier = Modifier.weight(1f),
            "Günbatımı",
            "19.05",
            R.drawable.sunset1,
            size = 64
        )
    }
}
@Composable
fun DetailSubPageCompDouble(weatherResponse: WeatherResponse) {
    Row(modifier = Modifier.padding(top = 10.dp)) {
        DetailSubPageComponents(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Hızı",
            speed = "${weatherResponse.current.windMph} km/s",
            imageId = R.drawable.windy,
            size = 64
        )
        DetailSubPageWindDirection(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Yönü",
            degree = "${weatherResponse.current.windDegree}°",
            imageId = R.drawable.compass2,
            size = 64
        )
    }
}
@Composable
fun DetailSubPageCompSecondComp(weatherResponse: WeatherResponse) {
    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        DetailSubPageHumidity(
            modifier = Modifier.weight(1f),
            title = "Nem",
            deger = "%${weatherResponse.current.humidity}",
            imageId = R.drawable.humidity,
            size = 64
        )
        DetailSubPageTemp(
            modifier = Modifier.weight(1f),
            title = "Hissedilen",
            temperature = "${weatherResponse.current.feelslikeC}°C",
            imageId = R.drawable.thermometer,
            size = 64
        )
    }
}
@Composable
fun DetailSubPageSun(modifier: Modifier, title: String, clock: String, imageId: Int, size: Int) {
    Row(
        modifier = modifier
            .height(size.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight()
        )
        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
            Text(
                text = clock,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun DetailSubPageComponents(
    modifier: Modifier,
    title: String,
    speed: String,
    imageId: Int,
    size: Int
) {
    Row(
        modifier = modifier
            .height(size.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
            Text(
                text = speed,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun DetailSubPageWindDirection(
    modifier: Modifier,
    title: String,
    degree: String,
    imageId: Int,
    size: Int
) {
    Row(
        modifier = modifier
            .height(size.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
            Text(
                text = degree,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun DetailSubPageHumidity(
    modifier: Modifier,
    title: String,
    deger: String,
    imageId: Int,
    size: Int
) {
    Row(
        modifier = modifier
            .height(size.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
            Text(
                text = deger,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun DetailSubPageTemp(
    modifier: Modifier,
    title: String,
    temperature: String,
    imageId: Int,
    size: Int
) {
    Row(
        modifier = modifier
            .height(size.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
            Text(
                text = temperature,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetayTitle() {
    DetailCityTitle(
        weatherResponse = WeatherResponse(
            location = Location(
                name = "Çorum",
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
        )
    )
}
@Preview(showBackground = true)
@Composable
fun DetayIcon() {
    DetailPageIcon(
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
        )
    )
}
@Preview(showBackground = true)
@Composable
fun DetayTemp() {
    DetailTemperature(
        weatherResponse = WeatherResponse(
            location = Location(
                name = "Çorum",
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
                    text = "Güneşli",
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
        )
    )
}
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailPreview() {
    AppTheme {
        Detail(
            navController = NavController(LocalContext.current),
            weatherResponse = WeatherResponse(
                location = Location(
                    name = "Çorum",
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
                        text = "Güneşli",
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
            )
        )
    }
}