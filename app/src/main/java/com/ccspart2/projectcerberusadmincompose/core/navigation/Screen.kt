package com.ccspart2.projectcerberusadmincompose.core.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Employees : Screen("employees")
}
