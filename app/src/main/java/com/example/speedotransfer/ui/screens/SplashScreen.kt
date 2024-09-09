package com.example.speedotransfer.ui.screens

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.ui.theme.Heading1
import com.example.speedotransfer.ui.theme.P500

@Composable
fun SplashScreen(navController: NavController, modifier: Modifier = Modifier) {
//    val scale = remember {
//        Animatable(0f)
//    }
//    LaunchedEffect(key1 = true) {
//        scale.animateTo(
//            targetValue = 0.2f,
//            animationSpec = tween(
//                durationMillis = 500,
//                easing = {
//                    OvershootInterpolator(1f).getInterpolation(it)
//                }
//            )
//        )
//        kotlinx.coroutines.delay(1000)
//        navController.navigate(Route.HOME){
//            popUpTo(Route.SPLASH) { inclusive = true }
//        }
//    }


    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
//                easing = {
//                    OvershootInterpolator(0f).getInterpolation(it)
//                }
            )
        )

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800
            )
        )

        // Navigate based on whether onboarding is complete or not
        val sharedPreferences = navController.context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isOnboardingComplete = sharedPreferences.getBoolean("onboarding_complete", false)

        if (isOnboardingComplete) {
            // Navigate to Login if onboarding is completed
            navController.navigate(Route.SIGN_IN) {
                popUpTo(Route.SPLASH) { inclusive = true }
            }
        } else {
            // Navigate to Onboarding if onboarding is not complete
            navController.navigate(Route.ON_BOARDING) {
                popUpTo(Route.SPLASH) { inclusive = true }
            }
        }

//         val   currency = "EGP"
//         val  transferAmount = 1000
//         val  senderName = "Asmaa Dosuky"
//         val receiverName = "Jonathon Smith"
//         val senderAccountNumberSuffix = "7890"
//         val receiverAccountNumberSuffix = "7890"
//
//
//        val name = "Fady Milad"
//        val accountId = "123"
//        val balance = 100000.0
//        val startDate = "2023-08-01"
//        val endDate = "2023-08-31"
//
//
//        navController.navigate(
//            "${Route.HOME}/$accountId/$startDate/$endDate/${balance.toFloat()}/$name/$currency"
//       //     route = "${Route.CONFIRM_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}"
//
//        ){
//            popUpTo(Route.SPLASH) { inclusive = true  }
//        }

//        navController.navigate(Route.HOME) {
//            popUpTo(Route.SPLASH) { inclusive = true }
//        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = P500)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                alpha = alpha.value,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Speedo Transfer",
            style = Heading1,
            color = Color(0xFFFFFFFF)
        )
    }
}