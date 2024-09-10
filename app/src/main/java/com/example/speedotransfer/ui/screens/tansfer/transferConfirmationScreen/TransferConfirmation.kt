package com.example.speedotransfer.ui.screens.tansfer.transferConfirmationScreen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.AppRoutes.Route.CONFIRMED_TRANSACTION
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.transfer.TransferRepoImpl
import com.example.speedotransfer.model.TransferRequest
import com.example.speedotransfer.ui.elements.BeneficiaryCard
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.StepsRow
import com.example.speedotransfer.ui.screens.tansfer.getInitials
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferConfirmationDesign(navController: NavController,
    transferAmount: Int,
    currency: String,
    senderName: String,
    receiverName: String,
    senderAccountNumberSuffix: String,
    receiverAccountNumberSuffix: String,
                               token :String,
    modifier: Modifier = Modifier
) {

    val transferViewModel: TransferViewModel = viewModel(
        factory = TransferViewModelFactory(
            TransferRepoImpl(
                APIClient
            )
        )
    )
    val transferResult by transferViewModel.transferResult.collectAsState()
    val context = LocalContext.current
//
//        transferResult?.let { result ->
//            if(result.status) {
//              // Trigger navigation callback
//                Log.d("TagTransfer", "Transfer response: ${result.status} is called after Click")
//
//                //Log.d("TagTransferInside1", "\"${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}\"\n")
////                navController.navigate(route = "${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}" )
//
//                    navController.navigate(route = "${Route.CONFIRMED_TRANSACTION}/${transferAmount.toFloat()}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}")
//
//
//                //    Log.d("TagTransferInside2", "\"${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}\"\n")
//            }}



    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Transfer",

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


                StepsRow(currentStep = 2)
                TransferAmountArea(transferAmount = transferAmount, currency = currency)

                AccountsDetailsArea(
                    senderName = senderName,
                    receiverName = receiverName,
                    senderAccountNumberSuffix = senderAccountNumberSuffix.toString(),
                    receiverAccountNumberSuffix = receiverAccountNumberSuffix.toString()
                )

                SpeedoButton(text = "Confirm", enabled = true,
                    isTransparent = false, ){
                        transferViewModel.transferMoney(
                            TransferRequest(
                               senderAccNumber =  senderAccountNumberSuffix,
                               receiverAccNumber= receiverAccountNumberSuffix,
                                amount = transferAmount,
                                sendCurrency = currency

                            )
                        )
                   //     Log.d("NavGraphFady", "Graph is: ${navController.graph.id}")
createNotificationchannel(context)
sendNotification(context,senderName,"Transaction","Transaction was done Successfully ")

                        navController.navigate(route = "${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}/${token}")



//                        Log.d("TagTransferinsdeCLick", "Transfer response: ${transferResult?.status} is called")
//                            if(transferResult?.status == "COMPLETED") {
//                                // Trigger navigation callback
//
//                                Log.d("TagTransferInside1", "\"${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}\"\n")
//                                navController.navigate(route = "${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}" )
//                                Log.d("TagTransferInside2", "\"${CONFIRMED_TRANSACTION}/${transferAmount}/${currency}/${senderName}/${receiverName}/${senderAccountNumberSuffix}/${receiverAccountNumberSuffix}\"\n")
//                            }



                    }




                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                SpeedoButton(text = "Previous", enabled = true, isTransparent = true ){


                    navController.popBackStack(route = "${Route.BEGIN_TRANSACTION}/{senderName}/{senderAccountNumberSuffix}/{currency}/{token}",
                        false
                    )

                }
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }


        })
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
    transferAmount: Int,
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



private fun createNotificationchannel(context: Context) {
        val name = "transactionNotification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("1", name, importance)
        channel.description = "When transaction is done Notification"
        // Register the channel with the system
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

private  fun sendNotification(context: Context,initials: String, title: String, text: String) {

    val initialsBitmap = createCircularInitialsBitmap(getInitials(initials))

    // Use MessagingStyle to add a conversation-style notification
    val messagingStyle = NotificationCompat.MessagingStyle(initials)
        .setConversationTitle(title)
        .addMessage(text, System.currentTimeMillis(), initials) // The message with initials as sender

    // Build the notification
    val builder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(R.drawable.notifications)
        .setStyle(messagingStyle)
        .setLargeIcon(initialsBitmap) // Set the initials bitmap as large icon
        .setContentTitle(title)
        .setContentText(text)
        .setAutoCancel(true)

    // Issue the notification
    NotificationManagerCompat.from(context).notify(99, builder.build())
}

fun createCircularInitialsBitmap(initials: String): Bitmap {
    val size = 128 // Size of the circle
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Draw the circular background
    val paint = Paint().apply {
        isAntiAlias = true
        color = P300.toArgb() // Set circle color here
    }
    val rect = RectF(0f, 0f, size.toFloat(), size.toFloat())
    canvas.drawOval(rect, paint)

    // Draw the initials text
    paint.apply {
        color = G0.toArgb()
        textSize = 48f // Set text size
        textAlign = Paint.Align.CENTER
    }
    val textX = size / 2f
    val textY = (size / 2f) - ((paint.descent() + paint.ascent()) / 2f)
    canvas.drawText(initials, textX, textY, paint)

    return bitmap
}

@Preview(showSystemUi = true)
@Composable
private fun TransferConfirmationPreview() {
//    TransferConfirmationDesign(
//        rememberNavController(),
//        currency = "EGP",
//        transferAmount = 1000,
//        senderName = "Asmaa Dosuky",
//        receiverName = "Jonathon Smith",
//        senderAccountNumberSuffix = "7890",
//        receiverAccountNumberSuffix = "7890"
//    )

}