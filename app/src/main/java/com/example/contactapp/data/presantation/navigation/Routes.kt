package com.example.contactapp.data.presantation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object MainScreen

    @Serializable
    object AddEditScreen

    @Serializable
    object SplashScreen

}