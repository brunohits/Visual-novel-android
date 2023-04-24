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
    NavHost(navController = navController, startDestination = "start_screen") {
        composable(Destination.start.route) { StartScreen(navController) }
        composable(Destination.enterName.route) { EnterNameScreen(navController) }
        composable(Destination.someScreen.route) { navBackStackEntry ->
            val elemId = navBackStackEntry.arguments?.getString("elemId")
            if (elemId == null) {
                Toast.makeText(LocalContext.current, "elemId is required", Toast.LENGTH_SHORT)
                    .show()
            } else {
                SomeScreen(navController, elemId.toInt())
            }
        }
    }
}
