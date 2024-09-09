package com.example.speedotransfer.ui.screens.tansfer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Client
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.FavListBeneficiaryCard
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.elements.StepsRow
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants
import kotlinx.coroutines.launch


/*
* 
* Todo TextFields edits , Stepper modification to match the Design
*
* */



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferAmountDesign(navController: NavController, senderName: String,
                         senderAccountNumberSuffix: String,currency: String, modifier: Modifier = Modifier) {

    val client = Client("Asmaa Dosuky", "7890")
    val list = listOf(client, client, client, client, client, client, client)

    val favouritesList: List<Client> = list

    val scrollState = rememberScrollState()
    var amount by rememberSaveable { mutableStateOf("") }

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


                StepsRow(currentStep = 1)
                AmountArea(amount = amount, onAmountChange = { amount = it })
                RecipientInformationArea(navController,
                    senderName,senderAccountNumberSuffix,amount,
                    currency,favouritesList, modifier)


            }

        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipientInformationArea(navController: NavController,senderName: String,senderAccountNumberSuffix: String,amount: String,currency: String,favouritesList: List<Client>, modifier: Modifier = Modifier) {
    var recipientName by rememberSaveable { mutableStateOf("") }
    var recipientAccountNumber by rememberSaveable { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp), verticalAlignment = Alignment.Top

    ) {
        Text(text = "Recipient Information", style = BodyRegular16, color = G700)
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = "Favourites Icon",
                tint = P300 // Assuming P300 is a Color
                , modifier = modifier.size(20.dp)
            )
            Text(
                text = "Favourites",
                style = BodyMedium14,
                color = P300,
                modifier = modifier.clickable { showBottomSheet = true }
            )
        }
    }

    SpeedoTextField(
        labelText = "Recipient Name",
        value = recipientName,
        onValueChange = { recipientName = it },
        placeholderText = "Enter Recipient Name",
        icon = R.drawable.transparent_image,
        keyboardOptions = KeyboardOptions.Default
    )

    SpeedoTextField(
        labelText = "Recipient Account",
        value = recipientAccountNumber,
        onValueChange = { recipientAccountNumber = it },
        placeholderText = "Enter Percipient Account Number",
        icon = R.drawable.transparent_image,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

    )

    Spacer(modifier = modifier.padding(bottom = 32.dp))
    SpeedoButton(text = "Continue", enabled = true, isTransparent = false ,){
        navController.navigate(
            route = "${Route.CONFIRM_TRANSACTION}/${amount}/${currency}/$senderName/${recipientName}/$senderAccountNumberSuffix/$recipientAccountNumber"
        )
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))




    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            scrimColor = Color.Black.copy(alpha = 0.8f), dragHandle = {}
        ) {
            // Sheet content

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = "Favourite Icon", tint = P300,
                        modifier = modifier
                            .size(24.dp)

                    )
                    Text(
                        text = "Favourite List",
                        style = BodyRegular16,
                        fontSize = 20.sp,
                        color = P300,
                        modifier = modifier.padding(start = 8.dp)
                    )
                }

                LazyColumn {
                    items(favouritesList) {
                        FavListBeneficiaryCard(
                            clientName = it.Name,
                            accountNumberSuffix = it.accountNumber,
                            modifier = Modifier.clickable {
                                // Set the recipient name and account number when a client is selected
                                recipientName = it.Name
                                recipientAccountNumber = it.accountNumber

                                // Dismiss the bottom sheet
                                scope.launch {
                                    sheetState.hide()
                                    showBottomSheet = false
                                }
                            }
                        )
                        Spacer(modifier = modifier.height(16.dp))

                    }
                }

            }


//            Button(onClick = {
//                scope.launch { sheetState.hide() }.invokeOnCompletion {
//                    if (!sheetState.isVisible) {
//                        showBottomSheet = false
//                    }
//                }
//            }) {
//
//                Text("Hide bottom sheet")
//            }
        }
    }


}


@Composable
fun AmountArea(  amount: String,
                 onAmountChange: (String) -> Unit,modifier: Modifier = Modifier) {

    Text(
        text = "How much are you sending?",
        style = TitleSemiBold,
        color = G900,
        modifier = modifier.padding(vertical = 24.dp)
    )

    Card(
        colors = CardDefaults.cardColors(G0), modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp), shape = RoundedCornerShape(8.dp)
    ) {


        Column(modifier.padding(top = 16.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)) {

            SpeedoTextField(
                labelText = "Amount",
                value = amount,
                onValueChange = onAmountChange ,
                placeholderText = "Enter amount",
                icon = R.drawable.transparent_image,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )


        }
    }


}


//Scaffold(
//floatingActionButton = {
//    ExtendedFloatingActionButton(
//        text = { Text("Show bottom sheet") },
//        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
//        onClick = {
//            showBottomSheet = true
//        }
//    )
//}
//) { contentPadding ->
//    // Screen content
//

// Button(onClick = {
//                scope.launch { sheetState.hide() }.invokeOnCompletion {
//                    if (!sheetState.isVisible) {
//                        showBottomSheet = false
//                    }
//                }
//            })


@Preview(showSystemUi = true, device = "spec:parent=pixel_5")
@Composable
private fun TransferAmountDesignPreview() {

    // StepsRow(2)
    val client = Client("Asmaa Dosuky", "7890")
    val list = listOf(client, client, client, client, client, client, client)
    TransferAmountDesign(navController= rememberNavController(),"Fady" , "4893","EGP")
}