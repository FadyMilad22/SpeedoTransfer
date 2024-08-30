package com.example.speedotransfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.speedotransfer.ui.elements.BeneficiaryCard
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoTransferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { //innerPadding ->
BeneficiaryCard(direction = R.string.from, clientName = "Fady Milad", accountNumberSuffix ="3426" )
                }
            }
        }
    }
}

// NavigationBar Trial
//See : https://developer.android.com/develop/ui/compose/navigation#bottom-nav
//@Composable
//fun BottomNavBar(modifier: Modifier = Modifier) {
//    BottomNavigation {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentDestination = navBackStackEntry?.destination
//        items.forEach { screen ->
//            BottomNavigationItem(
//                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
//                label = { Text(stringResource(screen.resourceId)) },
//                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                onClick = {
//                    navController.navigate(screen.route) {
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeedoTransferTheme {

    }
}