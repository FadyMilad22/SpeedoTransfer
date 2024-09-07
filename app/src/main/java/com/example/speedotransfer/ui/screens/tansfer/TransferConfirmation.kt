package com.example.speedotransfer.ui.screens.tansfer

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.BeneficiaryCard
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.StepsRow
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleSemiBold

@Composable
fun TransferConfirmationDesign(
    transferAmount: Double,
    currency: String,
    senderName: String,
    receiverName: String,
    senderAccountNumberSuffix: String,
    receiverAccountNumberSuffix: String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
            .verticalScroll(scrollState)
    ) {


        StepsRow(currentStep = 2)
        TransferAmountArea(transferAmount = transferAmount, currency = currency)

        AccountsDetailsArea(
            senderName = senderName,
            receiverName = receiverName,
            senderAccountNumberSuffix = senderAccountNumberSuffix,
            receiverAccountNumberSuffix =receiverAccountNumberSuffix
        )

        SpeedoButton(text = "Confirm", enabled = true, isTransparent = false)

        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        SpeedoButton(text = "Previous", enabled = false, isTransparent = true)
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
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
    Box(modifier = modifier.padding(bottom = 32.dp)) {
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
            painter = painterResource(id = R.drawable.transfer_arrows),
            contentDescription = "transfer arrows",
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.Center)
        )
    }

}


@Composable
fun TransferAmountArea(
    transferAmount: Double,
    currency: String,
    modifier: Modifier = Modifier
) {

    Column(
        Modifier
            .padding(top = 24.dp, bottom = 16.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$transferAmount $currency",
            style = TitleSemiBold,
            color = Color(0xFF24221E), modifier = modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Transfer amount",
            style = BodyRegular16,
            color = Color(0xFF565552),
            modifier = modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding()
                .fillMaxWidth()
        ) {
            Text(
                text = "Total amount",
                style = BodyMedium16,
                color = G900,
                modifier = modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "$transferAmount $currency", style = BodyRegular16, color = Color(
                    0xFF565552
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TransferConfirmationPreview() {
    TransferConfirmationDesign(
        currency = "EGP",
        transferAmount = 1000.0,
        senderName = "Asmaa Dosuky",
        receiverName = "Jonathon Smith",
        senderAccountNumberSuffix = "7890",
        receiverAccountNumberSuffix = "7890"
    )

}