package com.example.visualnovel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Destination(val route: String) {
    object first : Destination("first_screen")
    object second : Destination("second_screen")
    object someScreen : Destination("some_screen/{elemId}") {
        fun createRoute(elemId: Int) = "some_screen/$elemId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationAppHost(navController = navController)
        }
    }

}


@Composable
fun NavigationAppHost(navController: NavHostController) {
    val ctx = LocalContext.current
    NavHost(navController = navController, startDestination = "first_screen") {
        composable(Destination.first.route) { FirstScreen(navController) }
        composable(Destination.second.route) { SecondScreen(navController) }
        composable(Destination.someScreen.route) { navBackStackEntry ->
            val elemId = navBackStackEntry.arguments?.getString("elemId")
            if (elemId == null) {
                Toast.makeText(ctx, "elemId is required", Toast.LENGTH_SHORT).show()
            } else {
                SomeScreen(navController, elemId.toInt())
            }
        }
    }
}
