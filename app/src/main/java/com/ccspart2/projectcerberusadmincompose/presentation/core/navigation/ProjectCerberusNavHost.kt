package com.ccspart2.projectcerberusadmincompose.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee.AddNewEmployeeRoute
import com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails.EmployeeDetailsRoute
import com.ccspart2.projectcerberusadmincompose.presentation.routes.home.HomeRoute
import com.ccspart2.projectcerberusadmincompose.presentation.routes.seeAllEmployees.SeeAllEmployeesRoute

@Composable
fun ProjectCerberusNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeRoute(navController = navController)
        }
        composable(route = Screen.SeeAllEmployees.route) {
            SeeAllEmployeesRoute(navController = navController)
        }
        composable(route = Screen.AddNewEmployee.route) {
            AddNewEmployeeRoute(navController = navController)
        }
        composable(
            route = Screen.EmployeeDetail.route,
            arguments = listOf(
                navArgument(ArgumentDefinitions.EMPLOYEE_ID.path) {
                    type = NavType.StringType
                }
            )
        ) {
            EmployeeDetailsRoute(navController = navController)
        }
    }
}
