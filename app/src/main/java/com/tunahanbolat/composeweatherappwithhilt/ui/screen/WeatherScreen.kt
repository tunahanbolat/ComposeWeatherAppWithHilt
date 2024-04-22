package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.network.WeatherRepository
import com.tunahanbolat.composeweatherappwithhilt.network.WeatherService

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel,navController: NavController) {

    val weatherData = viewModel.weatherDataList.value
    val error = viewModel.error.value

    Scaffold(
        content = {
            LazyColumn(contentPadding = it) {
                items(items = weatherData) { item ->
                    WeatherRow(
                        modifier = Modifier,
                        weatherResponse = item,
                        navController
                    )
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(lang = "tr")
    }
}

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun WeatherRow(
    modifier: Modifier,
    weatherResponse: WeatherResponse,
    navController: NavController
) {

    ElevatedCard(
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth()
            .height(100.dp)
            .fillMaxWidth()
            .clickable {
                val weatherJson = Gson().toJson(weatherResponse)
                       navController.navigate("detay/$weatherJson")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(10.dp),
//        colors = CardDefaults.cardColors(
//            Color.Cyan
//        ),

    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = "https:${weatherResponse.current.condition.icon} ",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(275.dp)
                    .padding(start = 150.dp),
                contentScale = ContentScale.FillBounds,
                alpha = 0.8F
            )
//        Image(
//            painter = painterResource(id = R.drawable.rainy),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
//            val imageUrl =
//                "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExaGN3bDl4dXhxZWNnb205b3M1cGJzdG1wN2JieGMxODZya2l6NGY4diZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/t7Qb8655Z1VfBGr5XB/giphy.gif"
////                val imageUrl = "https:" + cityCurrent.current.condition.icon
//            val painter: Painter = rememberAsyncImagePainter(imageUrl)
//
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(imageUrl)
//                    .decoderFactory(ImageDecoderDecoder.Factory())
//                    .build(),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(6.dp))
//                    .fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )

            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Text(
                        text = weatherResponse.location.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                    )
                    Text(
                        text = weatherResponse.current.condition.text,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))

                Text(
                    text = "${weatherResponse.current.tempC} °C",
                    modifier = Modifier
                        .padding(end = 20.dp, top = 30.dp),
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.DarkGray
                )
            }

        }

//    Column {
//
//        AsyncImage(
//            model = "https:${weatherResponse.current.condition.icon} ",
//            contentDescription = null,
//        )
//
//        Text(text = "City: ${weatherResponse.location.name}")
//        Text(text = "Temperature: ${weatherResponse.current.tempC}")
//        Text(text = "Hava Durum: ${weatherResponse.current.condition.text}")
//    }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun cardView(){
}


