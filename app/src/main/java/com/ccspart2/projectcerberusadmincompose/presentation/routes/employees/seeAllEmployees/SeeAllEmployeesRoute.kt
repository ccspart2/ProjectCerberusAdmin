package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.seeAllEmployees

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.presentation.core.navigation.Screen
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.dialogs.LoadingDialog
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.itemcomponents.SeeAllItem
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SeeAllEmployeesRoute(navController: NavController) {
    val viewModel: SeeAllEmployeesViewModel = hiltViewModel()

    SeeAllEmployeesScreen(
        viewStateFlow = viewModel.viewState,
        onAddEmployeeClick = {
            navController.navigate(Screen.AddNewEmployee.route)
        },
        onSelectEmployeeClick = { employeeID ->
            LogUtils.info("The Selected Employee is: $employeeID")
            navController.navigate(Screen.EmployeeDetail.createRoute(employeeID))
        }
    )
}

@Composable
private fun SeeAllEmployeesScreen(
    viewStateFlow: StateFlow<SeeAllEmployeesState>,
    onAddEmployeeClick: () -> Unit,
    onSelectEmployeeClick: (String) -> Unit
) {
    val viewState by viewStateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainTopBar(
            // TODO Extract This
            title = "Employees",
            actionImageResource = R.drawable.baseline_person_add_alt_24,
            onActionClick = onAddEmployeeClick
        )

        if (viewState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                LoadingDialog()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                items(Employee.sortEmployeesByName(viewState.employeeList)) { employee ->
                    SeeAllItem(
                        itemLabel = "${employee.firstName} ${employee.lastName}",
                        onClick = {
                            onSelectEmployeeClick(employee.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 1024, heightDp = 600, showBackground = true)
private fun SeeAllEmployeesScreenPreview() {
    PreviewScreen {
        SeeAllEmployeesScreen(
            viewStateFlow = MutableStateFlow(
                SeeAllEmployeesState(
                    employeeList = listOf(Employee.mock()),
                    isLoading = false
                )
            ),
            onAddEmployeeClick = {},
            onSelectEmployeeClick = {}
        )
    }
}

@Composable
@Preview(widthDp = 1024, heightDp = 600, showBackground = true)
private fun SeeAllEmployeesScreenLoadingPreview() {
    PreviewScreen {
        SeeAllEmployeesScreen(
            viewStateFlow = MutableStateFlow(
                SeeAllEmployeesState(
                    isLoading = true
                )
            ),
            onAddEmployeeClick = {},
            onSelectEmployeeClick = {}
        )
    }
}
