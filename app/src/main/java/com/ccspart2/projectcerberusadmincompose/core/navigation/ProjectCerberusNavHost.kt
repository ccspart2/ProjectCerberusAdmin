package com.ccspart2.projectcerberusadmincompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccspart2.projectcerberusadmincompose.routes.employees.EmployeesRoute
import com.ccspart2.projectcerberusadmincompose.routes.home.HomeRoute

@Composable
fun ProjectCerberusNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            HomeRoute(navController = navController)
        }
        composable(route = Screen.Employees.route) {
            EmployeesRoute(navController = navController)
        }
    }
}
