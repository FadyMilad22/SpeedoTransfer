package com.example.speedotransfer

import android.os.Bundle
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
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.AppRoutes.AppNavHost
import com.example.speedotransfer.AppRoutes.getTopLevelRoute
import com.example.speedotransfer.model.Client
import com.example.speedotransfer.ui.screens.SplashScreen
import com.example.speedotransfer.ui.screens.more.FavouriteScreen
import com.example.speedotransfer.ui.screens.tansfer.TransferAmountDesign
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G200
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.SmallFontTextStyle
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme
import com.example.speedotransfer.ui.theme.TitleMedium


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { if (currentDestination != "splash") { // Replace "splash" with your actual splash route
                        BottomNavBar(navController)
                    } },
//                    topBar = { TopAppBar(title = { Text("Speedo Transfer", textAlign = TextAlign.Center, style = TitleMedium , fontSize = 20.sp, color = G700 , modifier =Modifier.padding(top= 8.dp)) }, backgroundColor = Color.Transparent)}
                 )
                { innerPadding ->

                    AppNavHost(navController=navController,modifier = Modifier.padding(innerPadding))
                   // val client = Client("Asmaa Dosuky", "7890")
                   // val list = listOf(client, client, client, client, client, client, client)
                   // FavouriteScreen(list)
                }

            }
        }
    }
}




@Composable
fun BottomNavBar(navController: NavController,modifier: Modifier = Modifier) {


    Card(
        modifier
            .fillMaxWidth()
            .navigationBarsPadding()  // This handles the navigation bar padding
            .systemBarsPadding(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = G0), shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp, bottomStart = 0.dp, bottomEnd = 0.dp)) {
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

            Row(horizontalArrangement = Arrangement.SpaceEvenly , modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)) {
                getTopLevelRoute().forEach { screen ->

                    BottomNavigationItem(
                        icon = {
                           Box(modifier =modifier.padding(start = 4.dp , end = 4.dp , bottom = 4.dp) ){ Icon(
                                painterResource(id = screen.icon),
                                contentDescription = "${screen.name} Icon",
                                modifier = modifier.size(24.dp),
                               tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) P300 else G200  // Tint selected item with P300 and unselected with G200

                           )}
                        },
                        label = {
                            Text(
                                screen.name,
                                style = SmallFontTextStyle,
                                fontSize = 11.sp,
                                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) P300 else G200,
                                maxLines = 1,  // Ensure the text stays on one line
                                softWrap = false  // Disable text wrapping
                                ,

                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
//                                popUpTo(navController.graph.findStartDestination()) {    // This line causes an Error
//                                    saveState = true
//                                }
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SpeedoTransferTheme {
        AppNavHost(rememberNavController())
    }
}