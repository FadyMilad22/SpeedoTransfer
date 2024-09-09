package com.example.speedotransfer.AppRoutes


import TransferConfirmedDesign
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.AppRoutes.Route.CONFIRMED_TRANSACTION
import com.example.speedotransfer.AppRoutes.Route.CONFIRM_TRANSACTION
import com.example.speedotransfer.AppRoutes.Route.HOME
import com.example.speedotransfer.AppRoutes.Route.SIGNIN
import com.example.speedotransfer.AppRoutes.Route.SIGNUP
import com.example.speedotransfer.AppRoutes.Route.SPLASH
import com.example.speedotransfer.ui.screens.SplashScreen
import com.example.speedotransfer.ui.screens.tansfer.transferConfirmationScreen.TransferConfirmationDesign
import com.example.speedotransfer.ui.screens.tansfer.TransferAmountDesign
import com.example.speedotransfer.ui.screens.authentication.signInScreen.SignInScreen
import com.example.speedotransfer.ui.screens.authentication.signUpScreen.SignUpScreen
import com.example.speedotransfer.ui.screens.tansfer.HomeScreen

object Route {
    const val SPLASH ="splash"
    const val HOME ="home"
    const val BEGIN_TRANSACTION = "beginTransaction"
    const val CONFIRM_TRANSACTION ="confirmTransaction"
    const val CONFIRMED_TRANSACTION ="confirmedTransaction"


    const val SIGNIN ="signin"
    const val SIGNUP ="signup"


}


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost( navController, startDestination = SPLASH, modifier = modifier) {
        // Add the routes
        composable(route = SPLASH) { SplashScreen(navController ,modifier=modifier) }

       // composable(route = HOME) { HomeScreen(navController = navController ,modifier=modifier) }
        // You can add other routes here in the future
        // Define the route as a string constant

        composable(
            route = "$HOME/{accountId}/{startDate}/{endDate}/{balance}/{name}/{currency}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("startDate") { type = NavType.StringType },
                navArgument("endDate") { type = NavType.StringType },
                navArgument("balance") { type = NavType.FloatType },
                navArgument("name") { type = NavType.StringType },
                navArgument("currency") { type = NavType.StringType }
            )
        ) {
            val accountId = it.arguments?.getLong("accountId")!!
            val startDate = it.arguments?.getString("startDate")!!
            val endDate = it.arguments?.getString("endDate")!!
            val balance = it.arguments?.getFloat("balance")!!.toDouble()
            val name = it.arguments?.getString("name")!!
            val currency = it.arguments?.getString("currency")!!

            HomeScreen(
                navController = navController,
                accountId = accountId,
                startDate = startDate,
                endDate = endDate,
                balance = balance,
                name = name,
                currency = currency
            )
        }

        composable(
            route = "$CONFIRMED_TRANSACTION/{transferAmount}/{currency}/{senderName}/{receiverName}/{senderAccountNumberSuffix}/{receiverAccountNumberSuffix}",
            arguments = listOf(
                navArgument("transferAmount") { type = NavType.IntType },  // Assuming transferAmount is passed as a float
                navArgument("currency") { type = NavType.StringType },
                navArgument("senderName") { type = NavType.StringType },
                navArgument("receiverName") { type = NavType.StringType },
                navArgument("senderAccountNumberSuffix") { type = NavType.StringType },
                navArgument("receiverAccountNumberSuffix") { type = NavType.StringType }
            )
        ) {
            val transferAmount = it.arguments?.getInt("transferAmount")!!  // Converting from Float to Double
            val currency = it.arguments?.getString("currency")!!
            val senderName = it.arguments?.getString("senderName")!!
            val receiverName = it.arguments?.getString("receiverName")!!
            val senderAccountNumberSuffix = it.arguments?.getString("senderAccountNumberSuffix")!!
            val receiverAccountNumberSuffix = it.arguments?.getString("receiverAccountNumberSuffix")!!

            // Call the Composable function CONFIRMEDTRANSACTION with the extracted parameters
            TransferConfirmedDesign(
                navController= navController,
                transferAmount = transferAmount,
                currency = currency,
                senderName = senderName,
                receiverName = receiverName,
                senderAccountNumberSuffix = senderAccountNumberSuffix,
                receiverAccountNumberSuffix = receiverAccountNumberSuffix
            )
        }



        composable(
            route = "$CONFIRM_TRANSACTION/{transferAmount}/{currency}/{senderName}/{receiverName}/{senderAccountNumberSuffix}/{receiverAccountNumberSuffix}",
            arguments = listOf(
                navArgument("transferAmount") {
                    type = NavType.IntType

                },
                navArgument("currency") {
                    type = NavType.StringType
                },
                navArgument("senderName") {
                    type = NavType.StringType
                },
                navArgument("receiverName") {
                    type = NavType.StringType
                },
                navArgument("senderAccountNumberSuffix") {
                    type = NavType.StringType
                },
                navArgument("receiverAccountNumberSuffix") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            // Extracting the arguments from the NavBackStackEntry
            val transferAmount = backStackEntry.arguments?.getInt("transferAmount")!!
            val currency = backStackEntry.arguments?.getString("currency")!!
            val senderName = backStackEntry.arguments?.getString("senderName")!!
            val receiverName = backStackEntry.arguments?.getString("receiverName")!!
            val senderAccountNumberSuffix = backStackEntry.arguments?.getString("senderAccountNumberSuffix") !!
            val receiverAccountNumberSuffix = backStackEntry.arguments?.getString("receiverAccountNumberSuffix")!!

            // Calling the TransferConfirmationDesign composable with default values if none are passed
            TransferConfirmationDesign(
                navController = navController,
                transferAmount = transferAmount,
                currency = currency,
                senderName = senderName,
                receiverName = receiverName,
                senderAccountNumberSuffix = senderAccountNumberSuffix,
                receiverAccountNumberSuffix = receiverAccountNumberSuffix
            )
        }


        composable(
            route = "${Route.BEGIN_TRANSACTION}/{senderName}/{senderAccountNumberSuffix}/{currency}",
            arguments = listOf(
                navArgument("senderName") {
                    type = NavType.StringType
                },
                navArgument("senderAccountNumberSuffix") {
                    type = NavType.StringType
                },
                navArgument("currency") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            // Extracting the arguments from the NavBackStackEntry and ensuring they are non-null using !!
            val senderName = backStackEntry.arguments?.getString("senderName")!!
            val senderAccountNumberSuffix = backStackEntry.arguments?.getString("senderAccountNumberSuffix")!!
            val currency = backStackEntry.arguments?.getString("currency")!!

            // Call the TransferAmountDesign composable with the extracted parameters
            TransferAmountDesign(
                navController = navController,
                senderName = senderName,
                senderAccountNumberSuffix = senderAccountNumberSuffix,
                currency = currency
            )
        }


        composable(route = SIGNIN) {
            SignInScreen(navController = navController)  // Pass the navController to SignInScreen for navigation
        }
        composable(route = SIGNUP) {
            SignUpScreen(navController = navController)  // Pass the navController to SignInScreen for navigation
        }
    }
}