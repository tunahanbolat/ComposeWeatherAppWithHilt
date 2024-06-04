package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tunahanbolat.composeweatherappwithhilt.R
import com.tunahanbolat.composeweatherappwithhilt.data.Condition
import com.tunahanbolat.composeweatherappwithhilt.data.Current
import com.tunahanbolat.composeweatherappwithhilt.data.Location
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.genova
import java.util.Calendar
import kotlin.math.roundToLong

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Detail(weatherResponse: WeatherResponse) {
    val backColor: Brush
    val gradientMorning = Brush.linearGradient(
        0.0f to colorResource(id = R.color.morning_top),
        500.0f to colorResource(id = R.color.morning_bottom),
        start = Offset.Zero,
        end = Offset.Infinite
    )
    val gradientEvening = Brush.linearGradient(
        0.0f to colorResource(id = R.color.background_bottom),
        500.0f to colorResource(id = R.color.back_top),
        start = Offset.Zero,
        end = Offset.Infinite
    )
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    backColor = if (hour in 6..18) {
        gradientMorning
    } else {
        gradientEvening
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DetailCityTitle(weatherResponse)
        DetailPageIcon(weatherResponse)
        DetailTemperature(weatherResponse)
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(15.dp),
                    color = Color(0x32616161)
                )
        ) {
            DetailSubPageDouble()
            Divider(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
            DetailSubPageCompDouble(weatherResponse)
            Divider(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
            DetailSubPageCompSecondComp(weatherResponse)
        }
    }
}

@Composable
fun DetailCityTitle(weatherResponse: WeatherResponse) {
    Column {
        Text(
            text = weatherResponse.location.name,
            fontSize = 32.sp,
            fontFamily = genova,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun DetailPageIcon(weatherResponse: WeatherResponse) {
    val shape = RoundedCornerShape(16.dp)
    Column(
        modifier = Modifier
            .background(
                shape = shape,
                color = Color(0x22616161)
            )
    ) {
        AsyncImage(
            model = "https:${weatherResponse.current.condition.icon} ",
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
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
            text = "${weatherResponse.current.tempC.roundToLong()}°C",
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = genova,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = weatherResponse.current.condition.text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 24.sp,
            fontFamily = genova,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DetailSubPageDouble() {
    Row(modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 12.dp)) {
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
    Row(modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 12.dp)) {
        DetailSubPageComponents(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Hızı",
            speed = "${weatherResponse.current.windMph} km/s",
            imageId = R.drawable.windsock1,
            size = 64
        )
        DetailSubPageWindDirection(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Yönü",
            degree = "${weatherResponse.current.windDegree}°",
            imageId = R.drawable.cardinalcompass,
            size = 64
        )
    }
}

@Composable
fun DetailSubPageCompSecondComp(weatherResponse: WeatherResponse) {
    Row(modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 12.dp)) {
        DetailSubPageHumidity(
            modifier = Modifier.weight(1f),
            title = "Nem",
            deger = "%${weatherResponse.current.humidity}",
            imageId = R.drawable.humidity2,
            size = 64
        )
        DetailSubPageTemp(
            modifier = Modifier.weight(1f),
            title = "Hissedilen",
            temperature = "${weatherResponse.current.feelslikeC}°C",
            imageId = R.drawable.thermo,
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
                fontFamily = genova,
                fontSize = 16.sp,
            )
            Text(
                text = clock,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                fontFamily = genova,
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
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
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
                fontFamily = genova,
                fontSize = 16.sp,
            )
            Text(
                text = speed,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = genova,
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
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
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
                fontFamily = genova,
                fontSize = 16.sp,
            )
            Text(
                text = degree,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = genova,
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
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
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
                fontFamily = genova,
                fontSize = 16.sp,
            )
            Text(
                text = deger,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = genova,
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
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
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
                fontFamily = genova,
                fontSize = 16.sp,
            )
            Text(
                text = temperature,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = genova,
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
                tempC = 20.0,
                tempF = 68.0,
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

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailPreview() {
    AppTheme {
        Detail(
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
                    tempC = 20.0,
                    tempF = 68.0,
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