package com.ccspart2.projectcerberusadmincompose.presentation.routes.seeAllEmployees

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.presentation.core.navigation.Screen
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen

@Composable
fun SeeAllEmployeesRoute(navController: NavController){

    SeeAllEmployeesScreen(
        onAddEmployeeClick = {
            navController.navigate(Screen.AddNewEmployee.route)
        }
    )

}

@Composable
private fun SeeAllEmployeesScreen(
    onAddEmployeeClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainTopBar(
            title = "Employees",
            actionImageResource = R.drawable.baseline_person_add_alt_24,
            onActionClick = onAddEmployeeClick
        )
    }

}

@Composable
@Preview(widthDp = 1024, heightDp = 600, showBackground = true)
private fun SeeAllEmployeesScreenPreview(){
    PreviewScreen {
        SeeAllEmployeesScreen(
            onAddEmployeeClick = {}
        )
    }
}