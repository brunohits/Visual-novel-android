package com.example.visualnovel

sealed class Destination(val route: String) {
    object start : Destination("start_screen")
    object enterName : Destination("enterName_screen")
    object someScreen : Destination("some_screen/{elemId}") {
        fun createRoute(elemId: Int) = "some_screen/$elemId"
    }
}