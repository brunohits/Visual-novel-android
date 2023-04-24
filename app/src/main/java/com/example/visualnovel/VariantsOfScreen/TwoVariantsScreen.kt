package com.example.visualnovel.VariantsOfScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.visualnovel.Destination
import com.example.visualnovel.R
import com.example.visualnovel.model.Screen

@Composable
fun TwoVariantsScreen(screen: Screen, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 20.dp)

    ) {
        Button(
            onClick = {
                navController.navigate(
                    (Destination.someScreen.createRoute(
                        screen.arrayOfVariants[0].nextId
                    ))
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = screen.arrayOfVariants[0].variantText,
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_medium))
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
    ) {
        Button(
            onClick = { navController.navigate(Destination.someScreen.createRoute(screen.arrayOfVariants[1].nextId)) },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = screen.arrayOfVariants[1].variantText,
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_medium))
            )
        }
    }
}