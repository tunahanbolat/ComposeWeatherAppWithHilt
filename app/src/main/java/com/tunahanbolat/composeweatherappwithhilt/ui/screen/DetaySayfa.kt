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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tunahanbolat.composeweatherappwithhilt.R
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.josefinsans
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.kaushan
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(navController: NavController, weatherResponse:WeatherResponse) {
    val gradient = Brush.linearGradient(
        0.0f to Color.Magenta,
        500.0f to Color.Cyan,
        start = Offset.Zero,
        end = Offset.Infinite
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
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
}

@Composable
fun DetailCityTitle(weatherResponse: WeatherResponse) {
Column(
    modifier = Modifier.padding(top = 5.dp),
    horizontalAlignment = Alignment.Start
    ) {
    Text(
        text = weatherResponse.location.name,
        fontSize = 32.sp,
        fontFamily = kaushan,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(all = 5.dp)
    )
}
}

@Composable
fun DetailPageIcon(weatherResponse: WeatherResponse) {

    Column(modifier = Modifier.padding(top = 10.dp)) {

        AsyncImage(
            model = "https:${weatherResponse.current.condition.icon} ",
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center
        )

//        Image(
//            painter = painterResource(id = R.drawable.sunrise),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp),
//            contentScale = ContentScale.FillBounds,
//            alignment = Alignment.Center
//        )
    }

}

@Composable
fun DetailTemperature(weatherResponse: WeatherResponse) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            ) {
        Text(text = "${weatherResponse.current.tempC}°C",
            fontSize = 64.sp,
            color = MaterialTheme.colorScheme.onSurface)
        Text(
            text = weatherResponse.current.condition.text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
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
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun DetailSubPageCompDouble(weatherResponse: WeatherResponse){
    Row(modifier = Modifier.padding(top = 1.dp)) {
        DetailSubPageComponents(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Hızı",
            speed = "${weatherResponse.current.windMph} km/s",
            imageId = R.drawable.windy,
            size = 64)

        DetailSubPageWindDirection(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Yönü",
            degree = "${weatherResponse.current.windDegree}°",
            imageId = R.drawable.compass2,
            size = 64)
    }
}

@Composable
fun DetailSubPageCompSecondComp(weatherResponse: WeatherResponse){
    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        DetailSubPageHumidity(
            modifier = Modifier.weight(1f),
            title = "Nem",
            deger = "%${weatherResponse.current.humidity}",
            imageId = R.drawable.humidity,
            size = 64)

        DetailSubPageTemp(
            modifier = Modifier.weight(1f),
            title = "Hissedilen",
            temperature = "${weatherResponse.current.feelslikeC}°C",
            imageId = R.drawable.thermometer,
            size = 64)
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
    size: Int) {
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
    size: Int) {
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
    size: Int) {
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
    size: Int) {
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

@Composable
fun Look1(){
    Column(
        modifier = Modifier.padding(top = 5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Çorum",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(all = 5.dp)
        )
    }
}
@Composable
fun Look2(){
        Image(
            painter = painterResource(id = R.drawable.sunrise),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
}
@Composable
fun Look3(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "19°C",
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface)
        Text(
            text = "Parçalı Bulutlu",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}
@Composable
fun Look4(){
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
fun Look5(){
    Row(modifier = Modifier.padding(top = 1.dp)) {
        DetailSubPageComponents(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Hızı",
            speed = "12.7 km/s",
            imageId = R.drawable.windy,
            size = 64)

        DetailSubPageWindDirection(
            modifier = Modifier.weight(1f),
            title = "Rüzgar Yönü",
            degree = "168°",
            imageId = R.drawable.compass2,
            size = 64)
    }
}
@Composable
fun Look6(){
    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        DetailSubPageHumidity(
            modifier = Modifier.weight(1f),
            title = "Nem",
            deger = "%72",
            imageId = R.drawable.humidity,
            size = 64)

        DetailSubPageTemp(
            modifier = Modifier.weight(1f),
            title = "Hissedilen",
            temperature = "18°C",
            imageId = R.drawable.thermometer,
            size = 64)
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DetayGor() {
//    DetailCityTitle()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DetayIcon() {
//    DetailPageIcon()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DetayTemp() {
//    DetailTemperature("","")
//}

//@Preview(showBackground = true)
//@Composable
//fun DetayBilesen() {
//    DetailSubPageSun(
//        modifier = Modifier,
//        "",
//        "",
//        R.drawable.sunset,
//        size = 64)
//}

//@Preview(showBackground = true)
//@Composable
//fun DetayComp() {
//    DetailSubPageComponents(
//        modifier = Modifier,
//        title = "",
//        speed = "",
//        imageId = R.drawable.windsock,
//        size = 64)
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookDetay() {
//    val gradient = Brush.linearGradient(
//        0.0f to colorResource(id = R.color.background_bottom),
//        500.0f to colorResource(id = R.color.background_top),
//        start = Offset.Zero,
//        end = Offset.Infinite
//    )

    val gradient = Brush.linearGradient(
        0.0f to Color.Magenta,
        500.0f to Color.Cyan,
        start = Offset.Zero,
        end = Offset.Infinite
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Look1()
            Look2()
            Look3()
            Look4()
            Look5()
            Look6()
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailAllComp() {
    AppTheme {
        LookDetay()
    }
}