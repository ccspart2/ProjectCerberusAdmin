package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeDetailsRoute(
    navController: NavController,
    employeeId: String? = ""
) {
    val viewModel = hiltViewModel<EmployeeDetailsViewModel>()
    EmployeeDetailsScreen(
        stateFlow = viewModel.viewState
    )
}

@Composable
private fun EmployeeDetailsScreen(
    stateFlow: StateFlow<EmployeeDetailsState>
) {
    val viewState by stateFlow.collectAsState()
    Text(text = viewState.name)
}

@Preview
@Composable
private fun EmployeeDetailsPreview() {
    PreviewScreen {
        EmployeeDetailsScreen(
            stateFlow = MutableStateFlow(
                EmployeeDetailsState(name = "EmployeeDetails")
            )
        )
    }
}
