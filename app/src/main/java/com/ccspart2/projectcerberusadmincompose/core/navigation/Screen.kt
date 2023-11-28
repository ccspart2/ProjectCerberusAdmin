package com.ccspart2.projectcerberusadmincompose.core.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
}
