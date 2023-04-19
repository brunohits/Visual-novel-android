package com.example.visualnovel

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.datastore.dataStore
import androidx.navigation.NavController
import com.example.visualnovel.datastore.StoreUserNickname
import com.google.gson.Gson

data class Screen(
    val id: Int,
    val header: String,
    val background: String,
    val arrayOfVariants: List<Variant>
)

data class Variant(
    val nextId: Int,
    val variantText: String
)

data class ArrayOfScreens(
    val screens: List<Screen>
)

@Composable
fun readTheJson(): List<Screen> {
    val jsonString = LocalContext.current.assets.open("screens.json")
        .bufferedReader().use { it.readText() }
    val screens = Gson().fromJson(jsonString, ArrayOfScreens::class.java).screens
    return screens
}

fun findById(screens: List<Screen>, id: Int): Int {
    var requiredIndex = 0
    var index = -1
    for (screen in screens) {
        index++
        if (screen.id == id) {
            requiredIndex = index
        }
    }
    return requiredIndex
}

@SuppressLint("DiscouragedApi")
@Composable
fun SomeScreen(navController: NavController, elemId: Int) {
    val screens = readTheJson()
    val reqIndex = findById(screens, elemId)
    val ctx = LocalContext.current
    val dataStore = StoreUserNickname(ctx)
    val savedNickname = dataStore.getNickname.collectAsState(initial = "")
    val drawableResourceId: Int =
        ctx.resources.getIdentifier(screens[reqIndex].background, "drawable", ctx.packageName)
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
            if (elemId == 3) {
                Text(
                    text = "Отлично, ${savedNickname.value!!}! Чем займемся?",
                    color = Color.White,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            } else {
                Text(
                    text = screens[reqIndex].header,
                    color = Color.White,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            }
        }

        if (elemId == 3) {
            val nextid1 = screens[reqIndex].arrayOfVariants[0].nextId
            val nextid2 = screens[reqIndex].arrayOfVariants[1].nextId
            val nextid3 = screens[reqIndex].arrayOfVariants[2].nextId
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 20.dp)

            ) {
                Button(
                    onClick = { navController.navigate((Destination.someScreen.createRoute(nextid1))) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = screens[reqIndex].arrayOfVariants[0].variantText,
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
                    .padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Destination.someScreen.createRoute(nextid2)) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = screens[reqIndex].arrayOfVariants[1].variantText,
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
                    onClick = { navController.navigate(Destination.someScreen.createRoute(nextid3)) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = screens[reqIndex].arrayOfVariants[2].variantText,
                        color = Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                }
            }
        }
        if (elemId in listOf(4, 5, 6, 7, 8, 11)) {
            val nextid1 = screens[reqIndex].arrayOfVariants[0].nextId
            val nextid2 = screens[reqIndex].arrayOfVariants[1].nextId
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 20.dp)

            ) {
                Button(
                    onClick = { navController.navigate((Destination.someScreen.createRoute(nextid1))) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = screens[reqIndex].arrayOfVariants[0].variantText,
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
                    onClick = { navController.navigate(Destination.someScreen.createRoute(nextid2)) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = screens[reqIndex].arrayOfVariants[1].variantText,
                        color = Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                }
            }
        }
        if (elemId in listOf(9, 10, 12, 13, 14)) {
            val nextid = screens[reqIndex].arrayOfVariants[0].nextId
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 100.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Destination.someScreen.createRoute(nextid)) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightBlue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = screens[reqIndex].arrayOfVariants[0].variantText,
                        color = Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                }
            }
        }
        if (elemId == 1) {
            //restart app
            val packageManager: PackageManager = LocalContext.current.packageManager
            val intent: Intent =
                packageManager.getLaunchIntentForPackage(LocalContext.current.packageName)!!
            val componentName: ComponentName = intent.component!!
            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
            LocalContext.current.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
        }

    }

}