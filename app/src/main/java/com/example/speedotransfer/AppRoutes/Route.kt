package com.example.speedotransfer.AppRoutes


import TransferAmountDesign
import TransferConfirmedDesign
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.AppRoutes.Route.ACCOUNT_INFO
import com.example.speedotransfer.AppRoutes.Route.CHANGE_PASSWORD
import com.example.speedotransfer.AppRoutes.Route.COMPLETE_SIGN_UP
import com.example.speedotransfer.AppRoutes.Route.CONFIRMED_TRANSACTION
import com.example.speedotransfer.AppRoutes.Route.CONFIRM_TRANSACTION
import com.example.speedotransfer.AppRoutes.Route.EDIT_PROFILE
import com.example.speedotransfer.AppRoutes.Route.HOME
import com.example.speedotransfer.AppRoutes.Route.MORE
import com.example.speedotransfer.AppRoutes.Route.ON_BOARDING
import com.example.speedotransfer.AppRoutes.Route.PERSONAL_INFO
import com.example.speedotransfer.AppRoutes.Route.PROFILE
import com.example.speedotransfer.AppRoutes.Route.SETTINGS
import com.example.speedotransfer.AppRoutes.Route.SIGN_IN
import com.example.speedotransfer.AppRoutes.Route.SIGN_UP
import com.example.speedotransfer.AppRoutes.Route.SPLASH
import com.example.speedotransfer.ui.screens.SplashScreen
import com.example.speedotransfer.ui.screens.authentication.signInScreen.SignInScreen
import com.example.speedotransfer.ui.screens.authentication.signUpScreen.CompleteSignUpScreen
import com.example.speedotransfer.ui.screens.authentication.signUpScreen.SignUpScreen
import com.example.speedotransfer.ui.screens.more.MoreScreenDesign
import com.example.speedotransfer.ui.screens.more.favourite.FavouriteScreen
import com.example.speedotransfer.ui.screens.onboarding.OnboardingScreen
import com.example.speedotransfer.ui.screens.profile.AccountInfoScreen
import com.example.speedotransfer.ui.screens.profile.ChangePasswordScreen
import com.example.speedotransfer.ui.screens.profile.EditProfileScreen.EditProfileScreen
import com.example.speedotransfer.ui.screens.profile.ProfileInfoScreen
import com.example.speedotransfer.ui.screens.profile.ProfileScreen
import com.example.speedotransfer.ui.screens.profile.SettingsScreen
import com.example.speedotransfer.ui.screens.tansfer.HomeScreen
import com.example.speedotransfer.ui.screens.tansfer.transferConfirmationScreen.TransferConfirmationDesign
import com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.notificationsScreen.NotificationScreenDesign
import com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.transactionScreen.TransactionDetailsScreen
import com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.transactionScreen.TransactionsScreen

object Route {
    const val SPLASH = "splash"
    const val ON_BOARDING = "onBoarding"
    const val HOME = "home"
    const val BEGIN_TRANSACTION = "beginTransaction"
    const val CONFIRM_TRANSACTION = "confirmTransaction"
    const val CONFIRMED_TRANSACTION = "confirmedTransaction"
    const val TRANSACTIONS_LIST = "transactions"
    const val TRANSACTION_DETAILS = "transactionDetails"

    const val SIGN_IN = "signIn"
    const val SIGN_UP = "signUp"

    const val MORE = "more"
    const val FAVOURITES = "favourites"
    const val PAYMENT_HISTORY = "paymentHistory"
    const val PROFILE = "profile"
    const val PERSONAL_INFO = "personalInfo"
    const val EDIT_PROFILE = "editProfile"
    const val CHANGE_PASSWORD = "changePassword"
    const val SETTINGS = "settings"
    const val ACCOUNT_INFO = "accountInfo"
    const val COMPLETE_SIGN_UP = "completeSignUp"
}


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController, startDestination = SPLASH, modifier = modifier) {
        composable(route = SPLASH) { SplashScreen(navController, modifier = modifier) }
        composable(route = ON_BOARDING) { OnboardingScreen(navController) }
        composable(
            route = "$COMPLETE_SIGN_UP/{name}/{email}/{password}", arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType })
        ) {
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val password = it.arguments?.getString("password")!!
            CompleteSignUpScreen(navController, name, email, password)
        }


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
                authToken = startDate,
                tokenType = endDate,
                balance = balance,
                name = name,
                currency = currency
            )
        }

        composable(
            route = "$CONFIRMED_TRANSACTION/{transferAmount}/{currency}/{senderName}/{receiverName}/{senderAccountNumberSuffix}/{receiverAccountNumberSuffix}/{token}",
            arguments = listOf(
                navArgument("transferAmount") {
                    type = NavType.IntType
                },  // Assuming transferAmount is passed as a float
                navArgument("currency") { type = NavType.StringType },
                navArgument("senderName") { type = NavType.StringType },
                navArgument("receiverName") { type = NavType.StringType },
                navArgument("senderAccountNumberSuffix") { type = NavType.StringType },
                navArgument("receiverAccountNumberSuffix") { type = NavType.StringType },
                navArgument("token") { type = NavType.StringType }
            )
        ) {
            val transferAmount =
                it.arguments?.getInt("transferAmount")!!  // Converting from Float to Double
            val currency = it.arguments?.getString("currency")!!
            val senderName = it.arguments?.getString("senderName")!!
            val receiverName = it.arguments?.getString("receiverName")!!
            val senderAccountNumberSuffix = it.arguments?.getString("senderAccountNumberSuffix")!!
            val receiverAccountNumberSuffix =
                it.arguments?.getString("receiverAccountNumberSuffix")!!
            val token = it.arguments?.getString("token")!!


            TransferConfirmedDesign(
                navController = navController,
                transferAmount = transferAmount,
                currency = currency,
                senderName = senderName,
                receiverName = receiverName,
                senderAccountNumberSuffix = senderAccountNumberSuffix,
                receiverAccountNumberSuffix = receiverAccountNumberSuffix,
                token = token  // handel it
            )
        }



        composable(
            route = "$CONFIRM_TRANSACTION/{transferAmount}/{currency}/{senderName}/{receiverName}/{senderAccountNumberSuffix}/{receiverAccountNumberSuffix}/{token}",
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
                },
                navArgument("token") { type = NavType.StringType },

                )
        ) { backStackEntry ->

            val transferAmount = backStackEntry.arguments?.getInt("transferAmount")!!
            val currency = backStackEntry.arguments?.getString("currency")!!
            val senderName = backStackEntry.arguments?.getString("senderName")!!
            val receiverName = backStackEntry.arguments?.getString("receiverName")!!
            val senderAccountNumberSuffix =
                backStackEntry.arguments?.getString("senderAccountNumberSuffix")!!
            val receiverAccountNumberSuffix =
                backStackEntry.arguments?.getString("receiverAccountNumberSuffix")!!
            val token = backStackEntry.arguments?.getString("token")!!

            TransferConfirmationDesign(
                navController = navController,
                transferAmount = transferAmount,
                currency = currency,
                senderName = senderName,
                receiverName = receiverName,
                senderAccountNumberSuffix = senderAccountNumberSuffix,
                receiverAccountNumberSuffix = receiverAccountNumberSuffix,
                token = token
            )
        }


        composable(
            route = "${Route.BEGIN_TRANSACTION}/{senderName}/{senderAccountNumberSuffix}/{currency}/{token}",
            arguments = listOf(
                navArgument("senderName") {
                    type = NavType.StringType
                },
                navArgument("senderAccountNumberSuffix") {
                    type = NavType.StringType
                },
                navArgument("currency") {
                    type = NavType.StringType
                },
                navArgument("token") { type = NavType.StringType },

                )
        ) { backStackEntry ->

            val senderName = backStackEntry.arguments?.getString("senderName")!!
            val senderAccountNumberSuffix =
                backStackEntry.arguments?.getString("senderAccountNumberSuffix")!!
            val currency = backStackEntry.arguments?.getString("currency")!!
            val token = backStackEntry.arguments?.getString("token")!!

            TransferAmountDesign(
                navController = navController,
                senderName = senderName,
                senderAccountNumberSuffix = senderAccountNumberSuffix,
                currency = currency,
                token = token
            )
        }

        composable(
            route = "${Route.TRANSACTIONS_LIST}/{accountId}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("startDate") { type = NavType.StringType },
                navArgument("endDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getLong("accountId")!!
            val startDate = backStackEntry.arguments?.getString("startDate")!!
            val endDate = backStackEntry.arguments?.getString("endDate")!!

            TransactionsScreen(
                navController = navController,
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }

        composable(
            route = "${Route.PAYMENT_HISTORY}/{accountId}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("startDate") { type = NavType.StringType },
                navArgument("endDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getLong("accountId")!!
            val startDate = backStackEntry.arguments?.getString("startDate")!!
            val endDate = backStackEntry.arguments?.getString("endDate")!!

            NotificationScreenDesign(
                navController = navController,
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }

        composable(route = SIGN_IN) {
            SignInScreen(navController = navController)
        }
        composable(route = SIGN_UP) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = "$MORE/{accountId}/{name}/{email}/{birthDate}/{country}/{token}/{createdDate}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("birthDate") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType },
                navArgument("token") { type = NavType.StringType },
                navArgument("createdDate") { type = NavType.StringType },
            )
        )

        {
            val accountId = it.arguments?.getLong("accountId")!!
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val birthDate = it.arguments?.getString("birthDate")!!
            val country = it.arguments?.getString("country")!!
            val token = it.arguments?.getString("token")!!
            val createDate = it.arguments?.getString("createdDate")!!

            MoreScreenDesign(
                navController = navController,
                accountId = accountId,
                name = name,
                email = email,
                birthDate = birthDate,
                country = country,
                token = token, createdDate = createDate
            )
        }


        composable(
            route = "$PROFILE/{accountId}/{name}/{email}/{birthDate}/{country}/{createdDate}/{token}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("birthDate") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType },
                navArgument("createdDate") { type = NavType.StringType },
                navArgument("token") { type = NavType.StringType }
            )
        )
        {
            val accountId = it.arguments?.getLong("accountId")!!
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val birthDate = it.arguments?.getString("birthDate")!!
            val country = it.arguments?.getString("country")!!
            val createdDate = it.arguments?.getString("createdDate")!!
            val token = it.arguments?.getString("token")!!
            ProfileScreen(
                navController = navController,
                accountId = accountId,
                name = name,
                email = email,
                birthDate = birthDate,
                country = country,
                dateCreated = createdDate, token = token, bankAccount = ""
            )
        }
        composable(
            route = "$SETTINGS/{accountId}/{name}/{email}/{birthDate}/{country}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("birthDate") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType },
            )
        )

        {
            val accountId = it.arguments?.getLong("accountId")!!
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val birthDate = it.arguments?.getString("birthDate")!!
            val country = it.arguments?.getString("country")!!
            SettingsScreen(
                navController = navController,
                accountId = accountId,
                name = name,
                email = email,
                birthDate = birthDate,
                country = country
            )
        }

        composable(
            route = "$EDIT_PROFILE/{accountId}/{name}/{email}/{birthDate}/{country}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("birthDate") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType },
            )
        )

        {
            val accountId = it.arguments?.getLong("accountId")!!
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val birthDate = it.arguments?.getString("birthDate")!!
            val country = it.arguments?.getString("country")!!
            EditProfileScreen(
                navController = navController,
                accountId = accountId,
                name = name,
                email = email,
                birthDate = birthDate,
                country = country
            )
        }
        composable(route = CHANGE_PASSWORD) {
            ChangePasswordScreen(navController = navController)
        }
        composable(
            route = "$PERSONAL_INFO/{accountId}/{name}/{email}/{birthDate}/{country}/{bankAccount}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("birthDate") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType },
                navArgument("bankAccount") { type = NavType.StringType },

                )

        ) {
            val accountId = it.arguments?.getLong("accountId")!!
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val birthDate = it.arguments?.getString("birthDate")!!
            val country = it.arguments?.getString("country")!!
            val bankAccount = it.arguments?.getString("bankAccount")!!

            ProfileInfoScreen(
                navController = navController,
                accountId = accountId,
                name = name,
                email = email,
                birthDate = birthDate,
                country = country,
                bankAccount = bankAccount
            )
        }
        composable(
            route = "$ACCOUNT_INFO/{accountDescription}/{accountName}/{accountNumber}/{accountType}/{active}/{balance}/{currency}",
            arguments = listOf(
                navArgument("accountDescription") { type = NavType.StringType },
                navArgument("accountName") { type = NavType.StringType },
                navArgument("accountNumber") { type = NavType.StringType },
                navArgument("accountType") { type = NavType.StringType },
                navArgument("active") { type = NavType.BoolType },
                navArgument("balance") { type = NavType.IntType },
                navArgument("currency") { type = NavType.StringType },

                )

        ) {
            val accountDescription = it.arguments?.getString("accountDescription")!!
            val accountName = it.arguments?.getString("accountName")!!
            val accountNumber = it.arguments?.getString("accountNumber")!!
            val accountType = it.arguments?.getString("accountType")!!
            val active = it.arguments?.getBoolean("active")!!
            val balance = it.arguments?.getInt("balance")!!
            val currency = it.arguments?.getString("currency")!!

            AccountInfoScreen(
                navController = navController,
                accountDescription = accountDescription,
                accountName = accountName,
                accountNumber = accountNumber,
                accountType = accountType,
                active = active,
                balance = balance,
                currency = currency,
            )
        }


        composable(
            route = "${Route.FAVOURITES}/{token}",
            arguments = listOf(navArgument("token") { type = NavType.StringType })
        ) {
            val token = it.arguments?.getString("token")!!
//            val client = Client("Asmaa Dosuky", "7890")
//            val list = listOf(client, client, client, client, client, client, client)
            FavouriteScreen(
                navController = navController,
//                favouriteList= list,
                token = token,
            )
        }



        composable(
            route = "${Route.TRANSACTION_DETAILS}/{transactionId}/{token}",
            arguments = listOf(navArgument("transactionId") { type = NavType.LongType },
            navArgument("token") { type = NavType.StringType })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getLong("transactionId")!!
            val token = backStackEntry.arguments?.getString("token")!!
            TransactionDetailsScreen(
                navController = navController,
                transactionId = transactionId,
                token = token
            )
        }


    }
}