package com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun AddNewEmployeeRoute(navController: NavController) {
    val viewModel: AddNewEmployeeViewModel = hiltViewModel()
    AddNewEmployeeScreen(
        viewStateFlow = viewModel.viewState,
        onSaveButtonClicked = { firstName, lastName, phoneNumber, email, isAdmin ->
            viewModel.handleEvent(
                AddNewEmployeeEvent.OnSaveButtonClicked(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber,
                    email = email,
                    isAdmin = isAdmin
                )
            )
        },
        onDismissAlertDialog = {
            navController.popBackStack()
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewEmployeeScreen(
    viewStateFlow: StateFlow<AddNewEmployeeState>,
    onSaveButtonClicked: (String, String, String, String, Boolean) -> Unit,
    onDismissAlertDialog: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isAdmin by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    val viewState by viewStateFlow.collectAsState()

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()

    ) {
        MainTopBar(
            title = "Add Employee",
            onActionClick = {}
        )

        when (viewState.employeeUploadState) {
            EmployeeUploadState.SUCCESS -> {
                AlertDialog(
                    title = {
                        Text(text = "Employee Created")
                    },
                    text = {
                        Text(
                            text = "$firstName $lastName  has been added to the Database. "
                        )
                    },
                    onDismissRequest = onDismissAlertDialog,
                    confirmButton = {
                        TextButton(
                            onClick = onDismissAlertDialog
                        ) {
                            Text("Ok")
                        }
                    }
                )
            }
            EmployeeUploadState.ERROR -> {
                AlertDialog(
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(
                            text = "There was an error creating the Employee. " +
                                "Please try again later."
                        )
                    },
                    onDismissRequest = onDismissAlertDialog,
                    confirmButton = {
                        TextButton(
                            onClick = onDismissAlertDialog
                        ) {
                            Text("Ok")
                        }
                    }
                )
            }

            EmployeeUploadState.PENDING -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                ) {
                    Text(text = "First Name", style = MaterialTheme.typography.labelMedium)
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Last Name", style = MaterialTheme.typography.labelMedium)
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Phone Number", style = MaterialTheme.typography.labelMedium)
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Email", style = MaterialTheme.typography.labelMedium)
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Switch(
                            checked = isAdmin,
                            onCheckedChange = { isAdmin = it },
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                        Text(text = "Admin")
                    }
                    Button(
                        onClick =
                        {
                            onSaveButtonClicked(
                                firstName,
                                lastName,
                                phoneNumber,
                                email,
                                isAdmin
                            )
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 1024, heightDp = 800, showBackground = true)
fun AddNewEmployeeScreenPreview() {
    PreviewScreen {
        AddNewEmployeeScreen(
            viewStateFlow = MutableStateFlow(AddNewEmployeeState()),
            onSaveButtonClicked = { _, _, _, _, _ -> },
            onDismissAlertDialog = {}
        )
    }
}
