package com.example.visualnovel.model

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
