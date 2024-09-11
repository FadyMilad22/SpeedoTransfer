package com.example.speedotransfer.ui.screens

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.P500
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    val leftImageOffset = remember { Animatable(-200f) }
    val rightImageOffset = remember { Animatable(200f) }

    val imageSize = remember { Animatable(160f) }


    LaunchedEffect(Unit) {
        scope.launch {

            leftImageOffset.animateTo(20f, animationSpec = tween(durationMillis = 300))
            rightImageOffset.animateTo(-20f, animationSpec = tween(durationMillis = 300))


            val sharedPreferences =
                navController.context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val isOnboardingComplete = sharedPreferences.getBoolean("onboarding_complete", false)

            if (isOnboardingComplete) {
                navController.navigate(Route.SIGN_IN) {
                    popUpTo(Route.SPLASH) { inclusive = true }
                }
            } else {
                navController.navigate(Route.ON_BOARDING) {
                    popUpTo(Route.SPLASH) { inclusive = true }
                }
            }
        }
    }


    val currency = "EGP"
    val transferAmount = 1000
    val senderName = "Asmaa Dosuky"
    val receiverName = "Jonathon Smith"
    val senderAccountNumberSuffix = "7890"
    val receiverAccountNumberSuffix = "7890"
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

//        val name = "Ahmed"
//        val accountId = "8"
//        val email = "aa@aaa.com"
//        val birthDate = "2023-08-01"
//        val country = "EG"
    val token = "111"
//        val bankAccount = "xxxx0000"
// 
//        //////////////
//        val accountDescription = "aaaaaaaaaaaaaaaaaaaaaaa"
//        val accountName = "Ahmed Gamal"
//        val accountNumber = "77"
//        val accountType = "vip"
//        val active = true
//        val balance = 11
//        val currency = "xxxx0000"
    //  navController.navigate(route = ON_BOARDING)

//        navController.navigate(
//            "$MORE/${accountId}/${name}/${email}/${birthDate}/${country}/${token}"        )
//"$ACCOUNT_INFO/{accountDescription}/{accountName}/{accountNumber}/{accountType}/{active}/{balance}/{currency}"
//        navController.navigate(
//            "$PERSONAL_INFO/${accountId}/${name}/${email}/${birthDate}/${country}/${bankAccount}"
//        )
//       //     route = "${Route.CONFIRM_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}"

//        ){
//            popUpTo(Route.SPLASH) { inclusive = true  }
//        }

//        navController.navigate(Route.SIGN_UP) {
//            popUpTo(Route.SPLASH) { inclusive = true }
//        }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(P500), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.right),
                contentDescription = "Left Image",
                modifier = modifier
                    .offset(x = leftImageOffset.value.dp)
                    .size(imageSize.value.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.left),
                contentDescription = "Right Image",
                modifier = modifier
                    .offset(x = rightImageOffset.value.dp)
                    .size(imageSize.value.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
