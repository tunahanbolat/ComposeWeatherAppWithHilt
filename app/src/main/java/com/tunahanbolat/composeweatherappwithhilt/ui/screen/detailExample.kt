package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            WeatherInfo("Jakarta 7", "Good Morning", 21, "THUNDERSTORM")
            Spacer(modifier = Modifier.height(16.dp))
            NextDaysInfo()
            Spacer(modifier = Modifier.height(16.dp))
            CurrentInfo("Sunrise", "5:34 am")
            CurrentInfo("Air Quality", "60 Moderate")
            CurrentInfo("Precipitation", "87%")
            CurrentInfo("Sunset", "6:34 am")
            CurrentInfo("Humidity", "87%")
            CurrentInfo("Light Rain", "34%")
        }
    }
}

@Composable
fun WeatherInfo(city: String, greeting: String, temperature: Int, condition: String) {
    Column {
        Text(text = city, fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)
        Text(text = greeting, fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
        Text(text = "$temperature°C", fontSize = 36.sp, style = MaterialTheme.typography.headlineMedium)
        Text(text = condition, fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun NextDaysInfo() {
    Text(text = "Next days", fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
}

@Composable
fun CurrentInfo(title: String, value: String) {
    Row {
        Text(text = title, fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = value, fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen()
}