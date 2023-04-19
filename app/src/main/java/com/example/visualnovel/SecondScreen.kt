package com.example.visualnovel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.visualnovel.datastore.StoreUserNickname
import kotlinx.coroutines.launch

@Composable
fun SecondScreen(navController: NavHostController) {
    val ctx = LocalContext.current
    val nickname = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val dataStore = StoreUserNickname(ctx)
    Image(
        painter = painterResource(id = R.drawable.with_kiryusha),
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier.fillMaxHeight(0.7f), verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.darkBlue))
                .padding(15.dp), contentAlignment = Alignment.BottomCenter

        ) {
            Text(
                text = "Привет! Меня зовут Кирюша. А тебя?",
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_medium))
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            TextField(
                value = nickname.value,
                placeholder = { Text(text = "Введите свое имя...") },
                onValueChange = { nickname.value = it },

                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
        ) {
            Button(
                onClick = { navController.navigate(Destination.someScreen.createRoute(3));
                          scope.launch { dataStore.saveNickname(nickname.value) }},
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.darkBlue)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Подтвердить",
                    color = Color.White,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            }
        }
    }
}

