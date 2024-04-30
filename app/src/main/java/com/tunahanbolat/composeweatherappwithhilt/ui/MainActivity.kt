package com.tunahanbolat.composeweatherappwithhilt.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.squareup.kotlinpoet.UNIT
import com.tunahanbolat.composeweatherappwithhilt.R
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.WeatherScreen
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.WeatherViewModel
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.MainActivity
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.Detail
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.ShimmerLoadingScreen
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadingEffect()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun SayfaGecis() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "anasayfa") {
            composable("anasayfa") {
                WeatherScreen(
                    viewModel = weatherViewModel,
                    navController = navController
                )
            }
            composable("detay/{weatherResponse}", arguments = listOf(
                navArgument("weatherResponse") { type = NavType.StringType }
            )) {
                val json = it.arguments?.getString("weatherResponse")
                val weatherResponse = Gson().fromJson(json, WeatherResponse::class.java)
                Detail(
                    navController = navController,
                    weatherResponse = weatherResponse
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun LoadingEffect() {
        var loadingState1 = weatherViewModel.loadingState.value

        LaunchedEffect(Unit) {

        }
        BackHandler( enabled = loadingState1) {}
        Surface(color = MaterialTheme.colorScheme.background) {
            if (loadingState1) {
                ShimmerLoadingScreen()
            } else {
                SayfaGecis()
            }
        }
    }
}