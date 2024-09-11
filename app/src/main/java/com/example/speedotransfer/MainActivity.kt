package com.example.speedotransfer

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.AppRoutes.AppNavHost
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.AppRoutes.Route.ACCOUNT_INFO
import com.example.speedotransfer.AppRoutes.Route.COMPLETE_SIGN_UP
import com.example.speedotransfer.AppRoutes.Route.MORE
import com.example.speedotransfer.AppRoutes.getTopLevelRoute
import com.example.speedotransfer.model.Account
import com.example.speedotransfer.model.CustomerResponse
import com.example.speedotransfer.ui.interactionDetector.InactivityHandler
import com.example.speedotransfer.ui.screens.authentication.signInScreen.getCurrentDate
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G200
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.SmallFontTextStyle
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme


class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("customerPrefs", MODE_PRIVATE)

        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.light(
//                android.graphics.Color.TRANSPARENT,
//                android.graphics.Color.BLUE
//            ),

        )
        setContent {
            SpeedoTransferTheme(darkTheme = false) {


                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route
                val authToken = sharedPreferences.getString("auth_token", "") ?: ""
                val tokenType = sharedPreferences.getString("token_type", "") ?: ""
                InactivityHandler(
                    navController = navController,
                  token = "${tokenType} ${authToken}"
                ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentDestination != Route.SPLASH && currentDestination != Route.SIGN_IN && currentDestination != Route.SIGN_UP && currentDestination != "$COMPLETE_SIGN_UP/{name}/{email}/{password}" && currentDestination != Route.ON_BOARDING) {
                            BottomNavBar(navController, sharedPreferences)
                        }
                    },

                    )
                { _ ->

                    AppNavHost(navController = navController)

                }

            }
            }
        }
    }
}


@Composable
fun BottomNavBar(
    navController: NavController,
    sharedPreferences: SharedPreferences,
    modifier: Modifier = Modifier
) {

    val (customer, account) = fetchCustomerDataFromPreferences(sharedPreferences)

    val authToken = sharedPreferences.getString("auth_token", "") ?: ""
    val tokenType = sharedPreferences.getString("token_type", "") ?: ""
    Log.d("API TransactionUI pref","$tokenType $authToken")


    Card(
        modifier
            .fillMaxWidth()
            .navigationBarsPadding()  // This handles the navigation bar padding
            .systemBarsPadding(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = G0),
        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        BottomNavigation(
            backgroundColor = G0,
            contentColor = G200,
            modifier = modifier
                .padding()
                .fillMaxWidth(),
            elevation = 0.dp
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                getTopLevelRoute().forEach { screen ->

                    BottomNavigationItem(
                        icon = {
                            Box(
                                modifier = modifier.padding(
                                    start = 4.dp,
                                    end = 4.dp,
                                    bottom = 4.dp
                                )
                            ) {
                                Icon(
                                    painterResource(id = screen.icon),
                                    contentDescription = "${screen.name} Icon",
                                    modifier = modifier.size(24.dp),
                                    tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) P300 else G200  // Tint selected item with P300 and unselected with G200

                                )
                            }
                        },
                        label = {
                            Text(
                                screen.name,
                                style = SmallFontTextStyle,
                                fontSize = 11.sp,
                                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) P300 else G200,
                                maxLines = 1,  // Ensure the text stays on one line
                                softWrap = false,  // Disable text wrapping

                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {

                            navController.navigate(
                                when (screen.name) {
                                    "Home" -> { "${Route.HOME}/${account.id.toLong()}/${authToken}}/${tokenType}/${account.balance.toFloat()}/${customer.name}/${account.currency}"
                                    }
                                    "Transfer" -> { "${Route.BEGIN_TRANSACTION}/${customer.name}/${account.accountNumber}/${account.currency}/${tokenType} ${authToken}"
                                    }
                                    "Transactions" -> { "${Route.TRANSACTIONS_LIST}/${account.id.toLong()}/${authToken}/${tokenType}"
                                    }
                                    "My card" ->{"$ACCOUNT_INFO/${account.accountDescription}/${account.accountName}/${account.accountNumber}/${account.accountType}/${account.active}/${account.balance}/${account.currency}"}
                                    else -> { "$MORE/${customer.id.toLong()}/${customer.name}/${customer.email}/${customer.birthDate}/Egypt/${tokenType} ${authToken}/${account.createdAt}" }
                                }
                            ){
//                                "${Route.HOME}/$accountId/$startDate/$endDate/${balance.toFloat()}/$name/$currency"){

                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {    // This line causes an Error
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }
}

fun fetchCustomerDataFromPreferences(sharedPreferences: SharedPreferences): Pair<CustomerResponse, Account> {
    val account = Account(
        accountDescription = sharedPreferences.getString("account_description", "") ?: "",
        accountName = sharedPreferences.getString("account_name", "") ?: "",
        accountNumber = sharedPreferences.getString("account_number", "") ?: "",
        accountType = sharedPreferences.getString("account_type", "") ?: "",
        active = sharedPreferences.getBoolean("account_active", true),
        balance = sharedPreferences.getInt("account_balance", 0),
        createdAt = sharedPreferences.getString("account_createdAt", "") ?: "",
        currency = sharedPreferences.getString("account_currency", "") ?: "",
        id = sharedPreferences.getInt("account_id", 0),
        updatedAt = sharedPreferences.getString("account_updatedAt", "") ?: ""
    )

    val customer = CustomerResponse(
        accounts = listOf(account),
        birthDate = sharedPreferences.getString("customer_birthDate", "") ?: "",
        createdAt = sharedPreferences.getString("customer_createdAt", "") ?: "",
        email = sharedPreferences.getString("customer_email", "") ?: "",
        gender = sharedPreferences.getString("customer_gender", "") ?: "",
        id = sharedPreferences.getInt("customer_id", 0),
        name = sharedPreferences.getString("customer_name", "") ?: "",
        phoneNumber = sharedPreferences.getString("customer_phoneNumber", "") ?: "",
        updatedAt = sharedPreferences.getString("customer_updatedAt", "") ?: "",
        username = sharedPreferences.getString("customer_username", "") ?: ""
    )

    return Pair(customer, account)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SpeedoTransferTheme {
        AppNavHost(rememberNavController())
    }
}