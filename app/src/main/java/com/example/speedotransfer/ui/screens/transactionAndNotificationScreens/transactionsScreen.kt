package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G200
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.TitleSemiBold

@Composable
fun TransactionsScreen(transactionsList: List<Transaction>, modifier: Modifier = Modifier) {



    Column(modifier=modifier.padding(start = 16.dp , end = 16.dp , top = 32.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {


        Text(
            text = "Your Last Transactions",
            style = TitleSemiBold,
            color = G900,
            modifier = modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(transactionsList) {
                TransactionsMenuItem(
                    it.isReceived,
                    it.isSucessful,
                    it.amount,
                    it.currency,
                    it.name,
                    it.cardNumber,
                    it.date,
                    it.cardType
                )
                Spacer(modifier = modifier.padding(bottom = 16.dp))
            }
        }

    }


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
    modifier: Modifier = Modifier
) {

    val direction = if (isReceive) "Receive" else "Sent"
    val status = if (isSuccessful) "Successful" else "Failed"
    val icon = if (isSuccessful) R.drawable.card2 else R.drawable.bank
    val statusBackgroundColor = if (isSuccessful) Color(0xFFEAF3EC) else Color(0xFFFFEFEF)
    val statusColor = if (isSuccessful) Color(0xFF118A30) else D300

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = G0)
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
//    TransactionsMenuItem(
//        true,
//        false,
//        "1000",
//        "USD",
//        "Fady Milad",
//        "4342",
//        "12 Jul 2024 09:00 PM",
//        paymentMethod = "Visa . MasterCard"
//    )
//
    val transaction1 = Transaction(
        name = "Ahmed Mohamed",
        cardType = "Visa . MasterCard",
        cardNumber = "1234",
        amount = "1000",
        date = "Today 11:00",
        status = "Received",
        currency = "EGP"
    )
    val transaction2 = Transaction(
        name = "Ahmed Mohamed",
        cardType = "Visa . MasterCard",
        cardNumber = "1234",
        amount = "1000",
        date = "Today 11:00",
        status = "Received",
        currency = "EGP"
        , isSucessful = true
    )
    val list = listOf(transaction1, transaction2, transaction1, transaction2, transaction1)


    TransactionsScreen(list)
}