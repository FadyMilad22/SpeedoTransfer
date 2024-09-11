package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.transactionScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.transaction.TransactionRepoImpl
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.screens.tansfer.RecentTransactionsArea
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G200
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.Heading3
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    navController: NavController,
    accountId: Long,    // Passed from the previous screen
    startDate: String,  // Passed from the previous screen
    endDate: String,    // Passed from the previous screen
    modifier: Modifier = Modifier
) {

    val transactionViewModel: TransactionViewModel = viewModel(
        factory = TransactionViewModelFactory(
            TransactionRepoImpl(
                APIClient
            )
        )
    )

        transactionViewModel.fetchTransactionHistory(startDate, endDate)
    Log.d("API TransactionUIF" , "$endDate $startDate")


    val transactionHistory by transactionViewModel.transactionHistory.collectAsState()
    val isLoading by transactionViewModel.isLoading.collectAsState()
    val errorMessage by transactionViewModel.errorMessage.collectAsState()



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Transactions",

                        )
                },
                Modifier.background(
                    brush = uiConstants.APP_BACKGROUND_COLOR
                ),

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                    .padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                Text(
                    text = "Your Last Transactions",
                    style = TitleSemiBold,
                    color = G900,
                    modifier = modifier.padding(bottom = 16.dp)
                )
                transactionHistory.let {
                    if (it.isNotEmpty()) {
                        // Log.d("API Test SignIN", customerState.toString())

                        LazyColumn {
                            items(transactionHistory) {
                                TransactionsMenuItem(
                                    accountId.toString() == it.toAccount,
                                    true,
                                    it.amount.toString(),
                                    "EGP",
                                    it.transactionId.toString(),
                                    if (accountId.toString() == it.fromAccount) it.toAccount else it.fromAccount,
                                    it.timestamp,
                                    "Successful Transaction",
                                    onClick = {
                                        // Navigate to the details screen by transaction ID
                                        navController.navigate("${Route.TRANSACTION_DETAILS}/${it.transactionId}/$endDate $startDate")
                                        Log.d("API Details @ transaction Screen","$endDate $startDate")

                                    }
                                )
                                Spacer(modifier = modifier.padding(bottom = 16.dp))
                            }

                        }


                    } else {

                        Text(text = "No Transactions Done yet", style = Heading3 , color = P300 ,modifier=Modifier.padding(24.dp))
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

                    }
                }


//
//                if (isLoading) {
//
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//
//                }






            }
            Spacer(modifier = modifier.height(64.dp))


        })
}


@Composable
fun TransactionsMenuItem(
    isReceive: Boolean,
    isSuccessful: Boolean,
    amount: String,
    currency: String,
    name: String,
    accountNumber: String, date: String,
    paymentMethod: String,
    onClick: () -> Unit, // New onClick parameter for handling clicks
    modifier: Modifier = Modifier
) {

    val direction = if (isReceive) "Receive" else "Sent"
    val status = if (isSuccessful) "Successful" else "Failed"
    val icon = if (isSuccessful) R.drawable.card2 else R.drawable.bank
    val statusBackgroundColor = if (isSuccessful) Color(0xFFEAF3EC) else Color(0xFFFFEFEF)
    val statusColor = if (isSuccessful) Color(0xFF118A30) else D300

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = G0),
        modifier = modifier.clickable { onClick() }
    ) {


        Row(
            verticalAlignment = Alignment.Top,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {


            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = modifier
                    .size(54.dp), colors = CardDefaults.cardColors(containerColor = P50)
            ) {
                Box(
                    contentAlignment = Alignment.Center, // Aligns content in both horizontal and vertical center
                    modifier = modifier.fillMaxSize() // Make Box take up the full size of the Card
                ) {
                    Image(
                        painter = painterResource(icon),
                        contentDescription = "Card Icon",
                        colorFilter = ColorFilter.tint(P300),
                        modifier = Modifier
                            .size(36.dp)

                    )
                }
            }


            Column(
                modifier = modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = name,
                    style = BodyMedium14,
                    color = G900,
                )
                Text(
                    text = "$paymentMethod. $accountNumber",
                    style = BodyRegular14,
                    fontSize = 12.sp, lineHeight = 18.sp,
                    color = G700,

                    )
                Text(
                    text = "$date - $direction",
                    style = BodyRegular14, lineHeight = 18.sp,
                    fontSize = 12.sp,
                    color = G100,
                )

                Text(
                    text = "$$amount",
                    style = BodyMedium16,
                    color = P300,
                    modifier = modifier.padding(top = 8.dp)
                )
            }

            Column(horizontalAlignment = Alignment.End) {


                Image(
                    painter = painterResource(R.drawable.chevron),
                    colorFilter = ColorFilter.tint(G200),
                    contentDescription = "Arrow Icon", modifier =
                    modifier
                        .size(24.dp)

                )

                Spacer(modifier = modifier.padding(bottom = 12.dp))

                Box(
                    modifier = modifier
                        .background(statusBackgroundColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.7.dp, vertical = 2.67.dp)


                ) {
                    Text(
                        text = status,
                        color = statusColor,
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        style = BodyRegular14,

                        )
                }
            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun TransactionsScreenPreview() {


    TransactionsScreen(
        navController = rememberNavController(),
        accountId = 1,
        startDate = "2023-01-01",
        endDate = "2023-12-31"
    )
}