package com.tunahanbolat.composeweatherappwithhilt.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.tunahanbolat.composeweatherappwithhilt.data.WeatherResponse
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.Detail
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.ShimmerLoadingScreen
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.WeatherScreen
import com.tunahanbolat.composeweatherappwithhilt.ui.screen.WeatherViewModel
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val isLoading = weatherViewModel.loadingState
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadingEffect(isLoading)
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
                    weatherResponse = weatherResponse
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun LoadingEffect(isLoading: State<Boolean>) {
        //      val loadingState = isLoading.value
        //      
        val loadingState = isLoading.value

        LaunchedEffect(Unit) {}
        BackHandler(enabled = loadingState) {}

        Surface(color = MaterialTheme.colorScheme.background) {
            if (loadingState) {
                ShimmerLoadingScreen()
            } else {
                SayfaGecis()
            }
        }
    }
}