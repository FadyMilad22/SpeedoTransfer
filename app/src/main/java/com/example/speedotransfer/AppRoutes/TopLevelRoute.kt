package com.example.speedotransfer.AppRoutes

import androidx.annotation.DrawableRes
import com.example.speedotransfer.AppRoutes.Route.ACCOUNT_INFO
import com.example.speedotransfer.AppRoutes.Route.MORE
import com.example.speedotransfer.R

data class TopLevelRoute(

    val name: String,
    val route: String,
    @DrawableRes val icon: Int
)


fun getTopLevelRoute(): List<TopLevelRoute> {

    val bottomNavigationRoutes = mutableListOf<TopLevelRoute>()

    bottomNavigationRoutes.add(
        TopLevelRoute(
            "Home",
            "${Route.HOME}/{accountId}/{startDate}/{endDate}/{balance}/{name}/{currency}",
            R.drawable.home
        )
    )
    bottomNavigationRoutes.add(
        TopLevelRoute(
            "Transfer",
            "${Route.BEGIN_TRANSACTION}/{senderName}/{senderAccountNumberSuffix}/{currency}/{token}",
            R.drawable.transfer1
        )
    )
    bottomNavigationRoutes.add(
        TopLevelRoute(
            "Transactions",
            "${Route.TRANSACTIONS_LIST}/{accountId}/{startDate}/{endDate}",
            R.drawable.history1
        )
    )
    bottomNavigationRoutes.add(TopLevelRoute("My card", "$ACCOUNT_INFO/{accountDescription}/{accountName}/{accountNumber}/{accountType}/{active}/{balance}/{currency}", R.drawable.cards1))
    bottomNavigationRoutes.add(
        TopLevelRoute(
            "More",
            "$MORE/{accountId}/{name}/{email}/{birthDate}/{country}/{token}/{createdDate}",
            R.drawable.more
        )
    )

    return bottomNavigationRoutes
}
