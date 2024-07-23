package com.ccspart2.projectcerberusadmincompose.presentation.core.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SeeAllEmployees : Screen("seeAllEmployees")
    object AddNewEmployee : Screen("addNewEmployee")

    object EmployeeDetail : Screen("employeeDetail/{employeeId}") {
        fun createRoute(employeeId: String) = "employeeDetail/$employeeId"
    }
}
