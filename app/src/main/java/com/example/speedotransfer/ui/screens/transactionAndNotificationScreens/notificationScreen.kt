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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.P75
import com.example.speedotransfer.ui.uiConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreenDesign(transactionsList: List<Transaction> ,modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Notifications",

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
                modifier = Modifier.padding(paddingValues)
                    .padding(horizontal = 16.dp)

    ) {


        LazyColumn {
            items(transactionsList) {
                NotificationMenuItem(
                    it.isReceived,
                    it.amount,
                    it.currency,
                    it.name,
                    it.cardNumber,
                    it.date
                )
                Spacer(modifier = modifier.padding(bottom = 16.dp))
            }
        }

    }

}
    )}


@Composable
fun NotificationMenuItem(
    isReceive: Boolean,
    amount: String,
    currency: String,
    name: String,
    accountNumber: String,date:String,
    modifier: Modifier = Modifier
) {

    val direction = if (isReceive) "Receive" else "Sent"

    val actionText =
        if (isReceive) "You have received $amount $currency from $name $accountNumber xxx"
        else "You have sent $amount $currency to $name $accountNumber xxx"

    val icon = if (isReceive) R.drawable.received else R.drawable.received

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = P50)
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
                    .size(54.dp)
                    .background(color = P50, shape = RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(containerColor = P50)
            ) {
                Box(
                    contentAlignment = Alignment.Center, // Aligns content in both horizontal and vertical center
                    modifier = modifier.fillMaxSize() // Make Box take up the full size of the Card
                ) {
                    Image(
                        painter = painterResource(icon),
                        contentDescription = "$direction Icon",
                        colorFilter = ColorFilter.tint(P300),
                        modifier = Modifier
                            .size(32.dp)
                            .background(P75, CircleShape)
                    )
                }
            }


            Column(modifier=modifier.padding(start = 16.dp), verticalArrangement = Arrangement.Center) {

                Text(
                    text = "$direction Transaction",
                    style = BodyMedium14,
                    color = G900,
                )
                Text(
                    text = actionText,
                    style = BodyRegular14,
                    fontSize = 12.sp, lineHeight = 18.sp,
                    color = G700
                    , modifier = modifier.padding(end = 16.dp)

                    )
                Text(
                    text = date,
                    style = BodyRegular14,lineHeight = 18.sp,
                    fontSize = 12.sp,
                    color = G100,
                )

            }
        }


    }

}


@Preview(showSystemUi = true)
@Composable
private fun NotificationScreenPreview() {
  //  NotificationMenuItem(true,"1000","USD" , "Fady Milad", "4342","12 Jul 2024 09:00 PM")
    // test code
    val transaction = Transaction(
        name = "Ahmed Mohamed",
        cardType = "Visa . MasterCard",
        cardNumber = "1234",
        amount = "500",
        date = "Today 11:00",
        status = "Received",
        currency = "EGP"
    )
    val list = listOf(transaction, transaction, transaction, transaction, transaction)


    NotificationScreenDesign(list)
}