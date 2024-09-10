package com.example.speedotransfer.ui.screens.more.favourite

import FavViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route.PROFILE
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.favourite.FavRepoImpl
import com.example.speedotransfer.data.repository.logout.LogoutRepoImpl
import com.example.speedotransfer.model.Client
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.FavListWithButtonsBeneficiaryCard
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.screens.more.logout.LogoutViewModel
import com.example.speedotransfer.ui.screens.more.logout.LogoutViewModelFactory
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants
import kotlinx.coroutines.launch


/*
* Error when Click on the Fields of the sheet
* Background Color
* On Delete
* */



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navController: NavController,
    token: String,
//    favouriteList: List<Client>,
    favViewModel: FavViewModel = viewModel(
        factory = FavViewModelFactory(
            FavRepoImpl(APIClient)
        )),


modifier: Modifier = Modifier
) {

// Observe ViewModel StateFlow
    val favourites by favViewModel.favourites.collectAsState()
    val isLoading by favViewModel.isLoading.collectAsState()
    val errorMessage by favViewModel.errorMessage.collectAsState()
    val deleteResponse by favViewModel.deleteResponse.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    var clickedListIndex by remember { mutableStateOf<Int?>(null) }

    //val sheetState = remember { SheetState(false) }
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var edit by remember { mutableStateOf<Boolean>(false) }

    var accountNumberEdit by remember { mutableStateOf("") }
    var accountNameEdit by remember { mutableStateOf("") }



    // Fetch the favourites when the screen loads
    LaunchedEffect(Unit) {
        favViewModel.getAllFav(token)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Favourite",

                        )
                },
                Modifier.background(
                    brush = uiConstants.BRUSH
                ),

                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack(
                            "$PROFILE/{accountId}/{name}/{email}/{birthDate}/{country}",
                            inclusive = false
                        ) // Navigate back to the profile screen

                    }) {
                        CustomAppBarIcon(
                            icon = R.drawable.drop_down
                        )
                    }
                },
            )
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text(
                    text = "Your favourite list",
                    style = TitleSemiBold,
                    color = G900,
                    modifier = modifier.padding(bottom = 16.dp)
                )

// Show loading state
                if (isLoading) {
                    CircularProgressIndicator()
                }
// Show error message
                errorMessage?.let {
                    Text(text = it, color = Color.Red, modifier = modifier.padding(bottom = 16.dp))
                }
                LazyColumn {
                    itemsIndexed(favourites) { index, item ->
                        FavListWithButtonsBeneficiaryCard(
                            clientName = item.recipientName,
                            accountNumberSuffix = item.accountNumber,
                            onEditClick = {
                                // Handle edit logic here
                            },
                            onDeleteClick = { name: String, accountNumber: String ->
                                // Handle delete logic here, use item.id for deletion
                                try {
                                    favViewModel.deleteFavourite(token, item.id)
                                    deleteResponse?.let { Log.d("delete ", it.message) }
                                } catch (e: Exception) {
                                    e.localizedMessage?.let { Log.d("delete ", it) }
                                }
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
                                    //.padding(end = 8.dp),
                                )
                                Text(
                                    text = "Edit",
                                    style = BodyRegular16,
                                    fontSize = 20.sp,
                                    color = G700,
                                    modifier = modifier.padding(start = 8.dp)

                                )
                            }



                            SpeedoTextField(
                                labelText = "Recipient Account",
                                value = accountNumberEdit, //.also { accountNameEdit = favouriteList.get(index = clickedListIndex!!).accountNumber },
                                onValueChange = { accountNumberEdit = it },
                                placeholderText = "Enter Cardholder Account Number",
                                icon = R.drawable.transparent_image,
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                            Spacer(modifier = modifier.padding(bottom = 8.dp))
                            SpeedoTextField(
                                labelText = "Recipient Name",
                                value = accountNameEdit, //.also { accountNameEdit = favouriteList.get(index = clickedListIndex!!).Name },
                                onValueChange = { accountNameEdit = it },
                                placeholderText = "Enter Cardholder Name",
                                icon = R.drawable.transparent_image,
                                keyboardOptions = KeyboardOptions.Default
                            )

                            Spacer(modifier = modifier.padding(bottom = 32.dp))
                            SpeedoButton(
                                text = "Save",
                                enabled = true,
                                isTransparent = true,
                                modifier = modifier.clickable { showBottomSheet = false })
                            Spacer(modifier = modifier.padding(bottom = 16.dp))

                        }

                    } else {

                        //Delete UI


                    }

                }

            }
        })
}


@Preview(showSystemUi = true)
@Composable
private fun FavouriteScreenPreview() {
    val client = Client("Asmaa Dosuky", "7890")
    val list = listOf(client, client, client, client, client, client, client)
//    FavouriteScreen(list)
}