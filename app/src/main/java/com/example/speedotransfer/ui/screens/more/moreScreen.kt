package com.example.speedotransfer.ui.screens.more

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.AppRoutes.Route.SIGN_IN
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.logout.LogoutRepoImpl
import com.example.speedotransfer.ui.uiConstants
import com.example.speedotransfer.ui.elements.ArrowedSmallMenuItem
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.screens.more.logout.LogoutViewModel
import com.example.speedotransfer.ui.screens.more.logout.LogoutViewModelFactory
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenDesign(
    navController: NavController,
    accountId: Long,    // Passed from the previous screen
    name: String,  // Passed from the previous screen
    email: String,    // Passed from the previous screen
    birthDate: String,    // Passed from the previous screen
    country: String,// Passed from the previous screen
    token: String,
    createdDate:String,
    modifier: Modifier = Modifier,
    logoutViewModel: LogoutViewModel = viewModel(
        factory = LogoutViewModelFactory(
            LogoutRepoImpl(APIClient)
        )

    )
) {

    val logoutState by logoutViewModel.logoutState.collectAsState()



    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val website = "https://www.banquemisr.com/en/Home/Pages/BM-Online"
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (logoutState?.httpStatus  == "OK") {
        // Show the success toast and navigate back to the profile screen
        Log.d("logout" , "${logoutState?.httpStatus}")
        Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
        navController.popBackStack(SIGN_IN, inclusive = false)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "More",

                        )
                },
                Modifier.background(
                    brush = uiConstants.BRUSH
                ),

                navigationIcon = {
                    IconButton(onClick = {}) {
                        CustomAppBarIcon(
                            icon = R.drawable.drop_down
                        )
                    }
                },
            )
        },
        bottomBar = {

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {


                ArrowedSmallMenuItem(name = "Transfer From Website",
                    icon = R.drawable.website,
                    modifier = modifier.clickable {
                        val i = Intent(Intent.ACTION_VIEW, website.toUri())
                        context.startActivity(i)
                    }
                )
                ArrowedSmallMenuItem(name = "Favourites",
                    icon = R.drawable.favorite,
                    modifier = modifier.clickable {
                        navController.navigate("${Route.FAVOURITES}/${token}")
                    }
                    )
                ArrowedSmallMenuItem(name = "Profile", icon = R.drawable.user, modifier = modifier.clickable {
                    navController.navigate(
                        route = "${Route.PROFILE}/${accountId}/${name}/$email/$birthDate/${country}/${createdDate}")

                })
                ArrowedSmallMenuItem(
                    name = "Help",
                    icon = R.drawable.compliance,
                    modifier = modifier.clickable { showBottomSheet = true })

                ArrowedSmallMenuItem(name = "logout", icon = R.drawable.logout, modifier = modifier.clickable {
                    logoutViewModel.logout(token)
                })
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                    scrimColor = Color.Black.copy(alpha = 0.8f), dragHandle = {}
                ) {
                    // Sheet content

                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = modifier
                            .padding(54.dp)
                            .fillMaxWidth()
                            .height(140.dp)
                    ) {

                        HelpBtmSheetCardEmail(icon = R.drawable.email,
                            title = "Send Email",
                            modifier.clickable {
                                val emailIntent = Intent(
                                    Intent.ACTION_SENDTO
                                ).apply {
                                    data = Uri.parse("mailto:" + "speedotransfer@example.com") // Replace with the desired email
                                    // Optional: Add extra data such as subject or body
                                    putExtra(Intent.EXTRA_SUBJECT, "Subject of the email")
                                    putExtra(Intent.EXTRA_TEXT, "Body of the email")
                                }
                                context.startActivity(emailIntent)

                        })
                        Spacer(modifier = modifier.width(32.dp))
                        HelpBtmSheetCardCall(icon = R.drawable.call,
                            title = "Call us", "000000",
                            modifier.clickable {
                            val intent = Intent(
                                Intent.ACTION_DIAL
                            ).apply {
                                data =
                                    Uri.parse("tel:" + "+201234561239")
                            }
                                context.startActivity(intent)

                            })


                    }


                }
            }


        })
}


@Composable
fun HelpBtmSheetCardEmail(@DrawableRes icon: Int, title: String, modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.width(120.dp),
        colors = CardDefaults.cardColors(containerColor = G0),
        shape = RoundedCornerShape(size = 8.dp)
    ) {

        Column(
            modifier = modifier
                .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 12.dp)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = modifier
                    .size(55.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(color = P50, shape = RoundedCornerShape(8.dp))
                // .padding(16.dp)

            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "$title Icon",
                    colorFilter = ColorFilter.tint(P300),
                    modifier =
                    modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }

            Text(
                text = title,
                style = BodyMedium14,
                color = G900,
                modifier = modifier.padding(top = 16.dp)
            )

        }

    }

}

@Composable
fun HelpBtmSheetCardCall(
    @DrawableRes icon: Int,
    title: String,
    number: String,
    modifier: Modifier = Modifier
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.width(120.dp),
        colors = CardDefaults.cardColors(containerColor = G0),
        shape = RoundedCornerShape(size = 8.dp),
    ) {

        Column(
            modifier = modifier
                .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 12.dp)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = modifier
                    .size(55.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(color = P50, shape = RoundedCornerShape(8.dp))
                // .padding(16.dp)

            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "$title Icon",
                    colorFilter = ColorFilter.tint(P300),
                    modifier =
                    modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }

            Text(
                text = title,
                style = BodyMedium14,
                color = G900,
                modifier = modifier.padding(top = 16.dp)
            )
            Text(text = number, style = BodyRegular14, color = P300)
        }

    }

}


@Preview(device = "spec:parent=pixel_5", showSystemUi = true)
@Composable
private fun MoreScreenPreview() {
//    MoreScreenDesign()
//HelpBtmSheetCardCall(icon = R.drawable.call, title = "Call Us" , "000000")
}