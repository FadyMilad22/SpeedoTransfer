package com.example.speedotransfer.ui.screens.more

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Client
import com.example.speedotransfer.ui.elements.FavListWithButtonsBeneficiaryCard
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G90
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.TitleSemiBold


/*
* edit Icon not in the right Size
* Error when Click on the Fields of the sheet
* Background Color
* On Delete
* */



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(favouriteList: List<Client>, modifier: Modifier = Modifier) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var edit by remember { mutableStateOf<Boolean>(false) }

    var accountNumberEdit by remember { mutableStateOf("") }
    var accountNameEdit by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            text = "Your favourite list",
            style = TitleSemiBold,
            color = G900,
            modifier = modifier.padding(bottom = 16.dp)
        )


        LazyColumn {
            items(favouriteList) {
                FavListWithButtonsBeneficiaryCard(
                    clientName = it.Name,
                    accountNumberSuffix = it.accountNumber,
                    onEditClick = {
                        edit = true
                        showBottomSheet = true
                        accountNameEdit = it.Name
                        accountNumberEdit = it.accountNumber
                    },
                    onDeleteClick = {
                        edit = false
                        showBottomSheet = true
                    }
                )
                Spacer(modifier = modifier.padding(bottom = 16.dp))
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false

            },
            sheetState = sheetState,
            scrimColor = Color.Black.copy(alpha = 0.8f),
        ) {
            if (edit) {

                //Edit Ui
                Column(
                    Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .fillMaxSize(),
                ) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Edit Icon", tint = P300,
                            modifier = modifier
                                .size(24.dp)
                                .padding(end = 8.dp),
                        )
                        Text(
                            text = "Edit",
                            style = BodyRegular16,
                            fontSize = 20.sp,
                            color = G700,
                        )
                    }



                    SpeedoTextField(
                        labelText = "Recipient Account",
                        value = accountNumberEdit,
                        onValueChange = { accountNumberEdit = it },
                        placeholderText = "Enter Cardholder Account Number",
                        icon = R.drawable.transparent_image,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = modifier.padding(bottom = 8.dp))
                    SpeedoTextField(
                        labelText = "Recipient Name",
                        value = accountNameEdit,
                        onValueChange = { accountNameEdit = it },
                        placeholderText = "Enter Cardholder Name",
                        icon = R.drawable.transparent_image,
                        keyboardOptions = KeyboardOptions.Default
                    )

                    Spacer(modifier = modifier.padding(bottom = 32.dp))
                    SpeedoButton(text = "Save", enabled = true, isTransparent = true)
                    Spacer(modifier = modifier.padding(bottom = 16.dp))

                }

            } else {

                //Delete UI
            }

        }

    }
}


@Preview(showSystemUi = true)
@Composable
private fun FavouriteScreenPreview() {
    val client = Client("Asmaa Dosuky", "7890")
    val list = listOf(client, client, client, client, client, client, client)
    FavouriteScreen(list)
}