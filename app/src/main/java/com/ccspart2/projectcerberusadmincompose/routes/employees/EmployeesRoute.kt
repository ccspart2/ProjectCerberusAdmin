package com.ccspart2.projectcerberusadmincompose.routes.employees

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.core.ui.preview.PreviewScreen

@Composable
fun EmployeesRoute(
    navController: NavController,
) {
    EmployeesScreen()
}

@Composable
private fun EmployeesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        MainTopBar(
            title = "Employees",
            actionImageResource = R.drawable.baseline_person_add_alt_24,
            onActionClick = {},
        )
    }
}

@Preview(widthDp = 1024, heightDp = 600)
@Composable
private fun EmployeesRoutePreview() {
    PreviewScreen {
        EmployeesScreen()
    }
}
