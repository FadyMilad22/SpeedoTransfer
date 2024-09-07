package com.example.speedotransfer.AppRoutes


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speedotransfer.AppRoutes.Route.HOME
import com.example.speedotransfer.AppRoutes.Route.SPLASH
import com.example.speedotransfer.ui.screens.SplashScreen
import com.example.speedotransfer.ui.screens.tansfer.HomeScreen

object Route {
    const val SPLASH ="splash"
    const val HOME ="home"
}


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost( navController, startDestination = SPLASH, modifier = modifier) {
        // Add the routes
        composable(route = SPLASH) { SplashScreen(navController ,modifier=modifier) }

        composable(route = HOME) { HomeScreen(navController ,modifier=modifier) }
        // You can add other routes here in the future
    }
}