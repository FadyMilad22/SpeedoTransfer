package com.example.speedotransfer.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.LinkMediumTextStyle
import com.example.speedotransfer.ui.theme.P300

@Composable
fun SignTrailingText(
    modifier: Modifier = Modifier,
    navController: NavController,
    distination: String,
    @StringRes question: Int,
    @StringRes answer: Int,
) {

//    Text(text = buildAnnotatedString {
//        withStyle(style = SpanStyle(color= G100)){
//            append(
//            String.format(
//                stringResource(id = R.string.sign_trailing), question, "")
//        )}
//
//       withStyle(style = SpanStyle(color= P300)) {
//           append(
//               String.format(
//                   stringResource(id = R.string.sign_trailing), "", answer
//               )
//           )
//       }
//
//    })

    Row() {
        Text(text = "${stringResource(id = question)} ", style = BodyRegular16, color = G100)
        Text(text = stringResource(id = answer),
            style = LinkMediumTextStyle,
            color = P300,
            textDecoration = TextDecoration.Underline,
            modifier = modifier.clickable {
                navController.navigate(distination)
            }
        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignTrailingTextPreview() {
//    SignTrailingText(question = R.string.already_have_an_account_q, answer = R.string.sign_in_a )

}