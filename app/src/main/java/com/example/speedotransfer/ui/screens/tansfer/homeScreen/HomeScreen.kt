package com.example.speedotransfer.ui.screens.tansfer.homeScreen

import HomeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.TransferRepoImpl
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G200
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.Heading2
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.UIConstants
import com.example.speedotransfer.ui.theme.D300

/**
 **
 **   ToDO Column Spacing to match the Design
 **
 * */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    accountId: Long,    // Passed from the previous screen
    startDate: String,  // Passed from the previous screen
    endDate: String,    // Passed from the previous screen
    balance :Double,    // Passed from the previous screen
    name: String,       // Passed from the previous screen
    currency: String,       // Passed from the previous screen
    modifier: Modifier = Modifier
) {
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(TransferRepoImpl(APIClient)))

    homeViewModel.fetchTransactionHistory(accountId, startDate, endDate)

    val transactionHistory by homeViewModel.transactionHistory.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val errorMessage by homeViewModel.errorMessage.collectAsState()





    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "",

                        )
                },
                Modifier.background(
                    brush = UIConstants.BRUSH
                ),

                )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top

            ) {


                NameBar(name = name)
                AmountCard(amount = balance, currency = currency)
                Spacer(modifier = Modifier.padding(top = 16.dp))
                RecentTransactionsArea(transactionList = transactionHistory)
                if (isLoading) {

                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

                }

                // Show error message if any
                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = D300,
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }

        }
    )
}

@Composable
fun RecentTransactionsArea(transactionList: List<TransactionResponse>, modifier: Modifier = Modifier) {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth().padding(bottom = 8.dp)) {

            Text(
                text = "Recent transactions",
                style = BodyMedium16,
                color = G900,
            )

            Text(
                text = "View all",
                style = BodyMedium16,
                color = G200,
            )


        }
        LazyColumn() {
            items(transactionList) {
                TransactionItem(transaction = it)
            }
        }
    }

}

@Composable
fun TransactionItem(transaction: TransactionResponse, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = G0)
    ) {
        Row(
            modifier
                .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {


            Row(modifier.fillMaxHeight()) {

                Box(
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .background(color = P50, shape = RoundedCornerShape(8.dp))
                        .width(64.dp)
                        .height(61.dp)

                ) {


                    Image(
                        painter = painterResource(R.drawable.master_card_background),
                        contentDescription = "",
                        modifier = modifier.align(Alignment.Center)
                    )
                    Image(
                        painter = painterResource(R.drawable.master_card_logo),
                        contentDescription = "",
                        modifier = modifier
                            .align(Alignment.Center)
                            .width(30.dp)
                            .height(18.dp)
                            .offset(0.dp, (-4).dp)

                        // .padding(horizontal = 16.dp, vertical = 18.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp)
                ) {

                    Text(
                        text = transaction.senderAccountId.toString(), style = BodyMedium14, color = G900
                    )



                    Text(
                        text = "${transaction.description}",//"${transaction.cardType} . ${transaction.cardNumber}",
                        style = BodyRegular14,
                        fontSize = 12.sp,
                        color = G700
                    )
//                    Text(text = transaction.cardNumber ,style = BodyRegular14, fontSize = 12.sp,
//                        color = G700)


                    Text(
                        text = "${transaction.transactionDate} - ${transaction.status}",
                        style = BodyRegular14,
                        fontSize = 12.sp,
                        color = G100
                    )
                    // Text(text = transaction.status)
                }

            }

            Text(
                text = "${transaction.amount}${transaction.currency}",
                style = BodyRegular14,
                fontSize = 12.sp,
                color = P300
            )

        }


        HorizontalDivider(thickness = 1.dp, color = G40)
    }

}


@Composable
fun AmountCard(amount: Double, currency: String, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(P300, RoundedCornerShape(8.dp)), verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 12.dp),
            colors = CardDefaults.cardColors(containerColor = P300),
        ) {
            Text(
                text = "Current Balance",
                style = BodyMedium16,
                color = G0,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Text(text = "$amount $currency", style = Heading2, color = G0)


        }

    }
}

@Composable
fun NameBar(name: String, modifier: Modifier = Modifier) {

    Row() {
        Box(
            modifier = modifier
                .size(48.dp)
                //.align(Alignment.CenterVertically)
                .background(color = G40, shape = CircleShape)
            // .padding(16.dp)

        ) {
            Text(
                getInitials(name),
                style = TitleSemiBold,
                color = G100,
                modifier = modifier.align(Alignment.Center)
            )

        }

        Column(
            modifier
                .padding(start = 8.dp)
                .weight(1f),
        ) {
            Text(text = "Welcome back,", style = BodyRegular14, color = P300)

            Text(text = name, style = BodyMedium16, color = G900, modifier = modifier.padding(4.dp))

        }

        Image(
            painter = painterResource(R.drawable.notifications),
            contentDescription = "Notification Icon",
            modifier = modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        )

    }
}


fun getInitials(name: String): String {
    val splitName = name.split(" ")
    var initials = ""
    for (part in splitName) {
        initials += part.substring(0, 1).uppercase()
    }
    return initials
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {



}