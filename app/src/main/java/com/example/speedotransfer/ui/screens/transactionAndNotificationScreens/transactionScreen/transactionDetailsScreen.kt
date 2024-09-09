package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.transactionScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.TransactionRepoImpl
import com.example.speedotransfer.ui.elements.BeneficiaryCard
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.Heading3
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsScreen(
    navController: NavController,
    transactionId : Long ,

//    senderName: String,
//    receiverName: String,
//    senderAccountNumberSuffix: String,
//    receiverAccountNumberSuffix: String,
//    amount: String, currency: String, reference: String, date: String,

    modifier: Modifier = Modifier
) {
    val transactionViewModel: TransactionViewModel = viewModel(factory = TransactionViewModelFactory(
        TransactionRepoImpl(
            APIClient
        )
    )
    )

    val transactionDetails by transactionViewModel.transactionDetails.collectAsState()

    LaunchedEffect(transactionId) {
        transactionViewModel.getTransactionById(transactionId)
    }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Successful Transactions",

                        )
                },
                Modifier.background(
                    brush = uiConstants.BRUSH
                ),

                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
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
            transactionDetails?.let { transaction ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
    ) {
                TransferDoneSuccesfullyArea(
                    amount = transaction.amount.toString(),
                    currency = transaction.currency
                )

                AccountsDetailsArea(
                    senderName = transaction.senderAccountId.toString(),
                    receiverName = transaction.recipientAccountId.toString(),
                    senderAccountNumberSuffix = transaction.senderAccountId.toString(),
                    receiverAccountNumberSuffix = transaction.recipientAccountId.toString()
                )
                TransactionInfoFields(
                    amount = transaction.amount.toString(),
                    currency = transaction.currency,
                    reference = transaction.id.toString(),
                    date = transaction.transactionDate
                )

}
    }


}
    )}

@Composable
fun TransactionInfoFields(
    amount: String,
    currency: String,
    reference: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = P50)
    ) {


        Column(
            verticalArrangement = Arrangement.Center, modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            TransferInfoRow(
                startField = "Transfer amount amount",
                endField = "$amount $currency",
                modifier = modifier.padding(vertical = 16.dp)
            )
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFE3E2E2))
            TransferInfoRow(
                startField = "Reference",
                endField = reference,
                modifier = modifier.padding(top = 16.dp)
            )
            TransferInfoRow(
                startField = "Date",
                endField = date,
                modifier = modifier.padding(top = 10.dp)
            )

        }


    }
}


@Composable
fun TransferInfoRow(startField: String, endField: String, modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = startField,
            style = BodyRegular16,
            color = G700,
        )

        Text(
            text = endField,
            style = BodyRegular14,
            color = G100,
        )
    }
}


@Composable
fun TransferDoneSuccesfullyArea(
    modifier: Modifier = Modifier,
    amount: String,
    currency: String
) {

    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.three_d_right_sign),
            contentDescription = "Right mark",
            Modifier
                .width(120.dp)
                .height(124.dp)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = G900)) {
                    append("$amount ")
                }
                withStyle(style = SpanStyle(color = P300)) {
                    append(currency)
                }
            },
            style = Heading3,

            modifier = modifier.padding(top = 16.dp)
        )

        Text(
            text = "Transfer amount", // lsa el text dh not done
            style = BodyRegular16,
            color = G700,
        )

        Text(
            text = "Send money", // lsa el text dh not done
            style = BodyMedium14,
            color = P300,
        )

    }
}


@Composable
fun AccountsDetailsArea(
    senderName: String,
    receiverName: String,
    senderAccountNumberSuffix: String,
    receiverAccountNumberSuffix: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(bottom = 16.dp)) {
        Column {
            BeneficiaryCard(
                direction = R.string.from,
                clientName = senderName,
                accountNumberSuffix = senderAccountNumberSuffix
            )
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            BeneficiaryCard(
                direction = R.string.to,
                clientName = receiverName,
                accountNumberSuffix = receiverAccountNumberSuffix
            )
        }
        Image(
            painter = painterResource(id = R.drawable.right_arrow_transactions),
            contentDescription = "transfer arrows",
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.Center)
        )
    }

}


@Preview(showSystemUi = true)
@Composable
private fun TransactionDetailsScreenPreview() {

    TransactionDetailsScreen(
    rememberNavController(),12L)

}