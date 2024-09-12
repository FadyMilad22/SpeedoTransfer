package com.example.speedotransfer.ui.screens.onboarding

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.Heading3
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P75
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navController: NavController, modifier: Modifier = Modifier) {
    val pages = listOf(
        OnboardingPage(
            title = "Amount",
            description = "Send money fast with simple steps. Create account, Confirmation, Payment. Simple.",
            imageRes = R.drawable.fast_loading_rafiki_1
        ),
        OnboardingPage(
            title = "Confirmation",
            description = "Transfer funds instantly to friends and family worldwide, strong shield protecting a name.",
            imageRes = R.drawable.currency_rafiki_1
        ),
        OnboardingPage(
            title = "Payment",
            description = "Enjoy peace of mind with our secure platform. Transfer funds instantly to friends.",
            imageRes = R.drawable.mobile_payments_rafiki_1
        )
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

//    // Auto-scroll effect
//    LaunchedEffect(key1 = pagerState.currentPage) {
//        if (pagerState.pageCount > 1) {
//            delay(3000) // Delay for 3 seconds before auto-scroll
//            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
//            pagerState.scrollToPage(nextPage)
//        }
//    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    TextButton(onClick = {
                        navController.navigate(Route.SIGN_IN) {
                            popUpTo(Route.ON_BOARDING) { inclusive = true }
                        }
                    }) {
                        Text(text = "Skip", style = BodyMedium16, color = G900)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                HorizontalPager(
                    count = pages.size,
                    state = pagerState,
//                    modifier = modifier.weight(0.5f)
                ) { page ->
                    OnboardingPageContent(pages[page], pagerState)
                }
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {


                        if (pagerState.currentPage == pages.size - 1) {

                            val sharedPreferences = navController.context.getSharedPreferences(
                                "app_prefs",
                                Context.MODE_PRIVATE
                            )
                            sharedPreferences.edit().putBoolean("onboarding_complete", true).apply()

                            navController.navigate(Route.SIGN_IN) {
                                popUpTo(Route.ON_BOARDING) { inclusive = true }
                            }
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(6.dp),
                    enabled = true,
                    colors = ButtonColors(
                        disabledContentColor = G0,
                        contentColor = G0,
                        disabledContainerColor = G100,
                        containerColor = P300
                    )

                ) {
                    Text(text = if (pagerState.currentPage == pages.size - 1) "Finish" else "Next")
                }
                Spacer(modifier = Modifier.height(48.dp))

            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = modifier
                .height(300.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(45.dp))

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            spacing = 10.dp,  //8
            indicatorWidth = 16.dp,
            indicatorHeight = 16.dp,
            activeColor = P300,
            inactiveColor = P75
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = page.title, style = Heading3, color = G900)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,
            style = BodyRegular16,
            color = G700,
            textAlign = TextAlign.Center
        )
    }
}


data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int
)