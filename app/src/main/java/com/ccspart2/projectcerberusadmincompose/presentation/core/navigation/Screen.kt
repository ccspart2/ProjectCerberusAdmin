package com.ccspart2.projectcerberusadmincompose.presentation.core.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Employees : Screen("employees")
}