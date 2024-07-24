package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.dialogs.LoadingDialog
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen
import com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails.components.EmployeeEditDialog
import com.ccspart2.projectcerberusadmincompose.ui.theme.ProjectCerberusAdminComposeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeDetailsRoute(
    navController: NavController
) {
    val viewModel = hiltViewModel<EmployeeDetailsViewModel>()
    EmployeeDetailsScreen(
        stateFlow = viewModel.viewState,
        onDisplayEditDialog = {
            viewModel.handleEvent(EmployeeDetailsEvent.OnDisplayEditDialog(true))
        },
        onDismissEditDialog = {
            viewModel.handleEvent(EmployeeDetailsEvent.OnDisplayEditDialog(false))
        },
        onConfirmEditDialog = { firstName, lastName, phone, email ->
            viewModel.handleEvent(
                EmployeeDetailsEvent.OnConfirmEditDialog(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phone,
                    email = email
                )
            )
        },
        onDismissInvalidInputDialog = {
            viewModel.handleEvent(EmployeeDetailsEvent.OnInvalidInputDismiss)
        }
    )
}

@Composable
private fun EmployeeDetailsScreen(
    stateFlow: StateFlow<EmployeeDetailsState>,
    onDisplayEditDialog: () -> Unit,
    onDismissEditDialog: () -> Unit,
    onConfirmEditDialog: (String, String, String, String) -> Unit,
    onDismissInvalidInputDialog: () -> Unit
) {
    val viewState by stateFlow.collectAsState()

    if (viewState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingDialog()
        }
    } else {
        viewState.selectedEmployee?.let { employee ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MainTopBar(
                    title = "Employee Details"
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        EmployeeDetailRow(label = "ID", value = employee.id)
                        EmployeeDetailRow(label = "First Name", value = employee.firstName)
                        EmployeeDetailRow(label = "Last Name", value = employee.lastName)
                        EmployeeDetailRow(label = "Phone Number", value = employee.phoneNumber)
                        EmployeeDetailRow(label = "Email", value = employee.email)
                        EmployeeDetailRow(
                            label = "Is Admin?",
                            value = if (employee.isAdmin) "Yes" else "No"
                        )
                        Button(
                            modifier = Modifier.padding(10.dp),
                            onClick = onDisplayEditDialog
                        ) {
                            Text(text = "Edit")
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                    }
                }
            }
            if (viewState.openEditDialog) {
                EmployeeEditDialog(
                    firstName = employee.firstName,
                    lastName = employee.lastName,
                    phoneNumber = employee.phoneNumber,
                    email = employee.email,
                    onDismissRequest = onDismissEditDialog,
                    onConfirmEditRequest = { firstName, lastName, phone, email ->
                        onConfirmEditDialog(firstName, lastName, phone, email)
                    }
                )
            }

            if (viewState.displayErrorDialog) {
                AlertDialog(
                    title = {
                        Text(text = "Invalid Input")
                    },
                    text = {
                        Text(
                            text = " One or more provided fields are invalid. Please try again."
                        )
                    },
                    onDismissRequest = onDismissInvalidInputDialog,
                    confirmButton = {
                        TextButton(onClick = onDismissInvalidInputDialog) {
                            Text(text = "Ok")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun EmployeeDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .width(150.dp)
        )
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .height(30.dp)
                .width(1.dp)
        )
        Text(
            textAlign = TextAlign.Start,
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .width(300.dp)
                .padding(horizontal = 10.dp)
        )
    }
}

@Preview(widthDp = 1024, heightDp = 600, showBackground = true)
@Composable
private fun EmployeeDetailsPreview() {
    PreviewScreen {
        EmployeeDetailsScreen(
            stateFlow = MutableStateFlow(
                EmployeeDetailsState(
                    selectedEmployee = Employee.mock(),
                    isLoading = false,
                    openEditDialog = false,
                    displayErrorDialog = true
                )
            ),
            onConfirmEditDialog = { _, _, _, _ -> },
            onDismissEditDialog = {},
            onDisplayEditDialog = {},
            onDismissInvalidInputDialog = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmployeeDetailRowPreview() {
    ProjectCerberusAdminComposeTheme {
        EmployeeDetailRow(
            "First Name",
            "Charlie"
        )
    }
}
