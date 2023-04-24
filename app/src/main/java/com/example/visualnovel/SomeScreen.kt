package com.example.visualnovel

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.visualnovel.VariantsOfScreen.OneVariantScreen
import com.example.visualnovel.VariantsOfScreen.ThreeVariantsScreen
import com.example.visualnovel.VariantsOfScreen.TwoVariantsScreen
import com.example.visualnovel.datastore.StoreUserNickname
import com.example.visualnovel.model.ArrayOfScreens
import com.example.visualnovel.model.Screen
import com.google.gson.Gson


@Composable
fun readTheJson(): List<Screen> {
    val jsonString = LocalContext.current.assets.open("screens.json")
        .bufferedReader().use { it.readText() }
    val screens = Gson().fromJson(jsonString, ArrayOfScreens::class.java).screens
    return screens
}


@SuppressLint("DiscouragedApi")
@Composable
fun SomeScreen(navController: NavController, elemId: Int) {
    val screens = readTheJson()
    val screen = screens.find { it.id == elemId }
    val dataStore = StoreUserNickname(LocalContext.current)
    val savedNickname = dataStore.getNickname.collectAsState(initial = "")
    val drawableResourceId: Int =
        LocalContext.current.resources.getIdentifier(
            screen?.background,
            "drawable",
            LocalContext.current.packageName
        )
    Image(
        painter = painterResource(id = drawableResourceId),
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .padding(bottom = 15.dp)
                .background(colorResource(id = R.color.darkBlue)),
            contentAlignment = Alignment.Center

        ) {
            Text(
                if (elemId == 3) "Отлично, ${savedNickname.value!!}! Чем займемся?" else screen?.header.toString(),
                color = Color.White,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_medium))
            )

        }
        if (elemId == 3) ThreeVariantsScreen(screen!!, navController)
        if (elemId in listOf(4, 5, 6, 7, 8, 11)) TwoVariantsScreen(screen!!, navController)
        if (elemId in listOf(9, 10, 12, 13, 14)) OneVariantScreen(screen!!, navController)
    }

}
