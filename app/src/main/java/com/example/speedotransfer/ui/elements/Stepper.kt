package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300


@Composable
fun StepIndicator(
    isActive: Boolean = true,
    stepNumber: String,
    stepLabel: String
) {
    val boxColor =
        if (isActive) P300 else Color(0xFFA3AAB2)
    val numberColor = if (isActive) P300 else Color(0xFF898886)
    val textColor = if (isActive) G900 else G700
    val textStyle = if (isActive) BodyMedium14 else BodyRegular14
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.run {
                size(36.dp)
                    .border(BorderStroke(2.24.dp, boxColor), shape = CircleShape)
            },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber,
                color = numberColor,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stepLabel,
            color = textColor,
            style = textStyle
        )
    }
}

//@Composable
//fun StepsRow(currentStep: Int) {
//    val steps = listOf("Amount", "Confirmation", "Payment")
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        for ((index, step) in steps.withIndex()) {
//            StepIndicator(
//                isActive = index + 1 <= currentStep,
//                stepNumber = String.format("%02d", index + 1),
//                stepLabel = step
//            )
//
//            if (index < steps.size - 1) {
//                Divider(
//                    modifier = Modifier
//                        .width(85.dp)
//                        .height(1.5.dp),
//                    color = if (index + 1 < currentStep) P300 else Color(0xFFA3AAB2)
//                )
//            }
//        }
//    }
//}

@Composable
fun StepsRow(currentStep: Int) {
    val steps = listOf("Amount", "Confirmation", "Payment")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for ((index, step) in steps.withIndex()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                StepIndicator(
                    isActive = index + 1 <= currentStep,
                    stepNumber = String.format("%02d", index + 1),
                    stepLabel = step
                )
            }

            if (index < steps.size - 1) {
                //    Spacer(modifier = Modifier.width(8.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(36.dp)
                    //  .padding(horizontal = 4.dp)
                ) {
                    Divider(
                        modifier = Modifier
                            .width(85.dp)
                            .height(1.5.dp),
                        color = if (index + 1 < currentStep) P300 else Color(0xFFA3AAB2)
                    )
                }
                //   Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun StepIndicatorPreview() {
    StepsRow(1)
}