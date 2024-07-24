package com.ccspart2.projectcerberusadmincompose.presentation.core.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SeeAllEmployees : Screen("seeAllEmployees")
    object AddNewEmployee : Screen("addNewEmployee")

    object EmployeeDetail : Screen("employeeDetail/{${ArgumentDefinitions.EMPLOYEE_ID.path}}") {
        fun createRoute(employeeId: String) = "employeeDetail/$employeeId"
    }
}
