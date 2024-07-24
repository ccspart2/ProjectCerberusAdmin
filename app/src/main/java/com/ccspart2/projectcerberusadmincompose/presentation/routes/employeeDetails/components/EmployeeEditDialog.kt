package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ccspart2.projectcerberusadmincompose.ui.theme.ProjectCerberusAdminComposeTheme

@Composable
fun EmployeeEditDialog(
    firstName: String,
    lastName: String,
    phoneNumber: String,
    email: String,
    onDismissRequest: () -> Unit,
    onConfirmEditRequest: (
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String
    ) -> Unit
) {
    var dialogFirstName by remember { mutableStateOf(firstName) }
    var dialogLastName by remember { mutableStateOf(lastName) }
    var dialogPhoneNumber by remember { mutableStateOf(phoneNumber) }
    var dialogEmail by remember { mutableStateOf(email) }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserInputField(
                    fieldLabel = "First Name",
                    fieldValue = dialogFirstName,
                    onValueChanged = {
                        dialogFirstName = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                UserInputField(
                    fieldLabel = "Last Name",
                    fieldValue = dialogLastName,
                    onValueChanged = {
                        dialogLastName = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                UserInputField(
                    fieldLabel = "Phone Number",
                    fieldValue = dialogPhoneNumber,
                    onValueChanged = {
                        dialogPhoneNumber = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )
                UserInputField(
                    fieldLabel = "Email",
                    fieldValue = dialogEmail,
                    onValueChanged = {
                        dialogEmail = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Button(
                    modifier = Modifier
                        .padding(10.dp),
                    onClick = {
                        onConfirmEditRequest(
                            dialogFirstName,
                            dialogLastName,
                            dialogPhoneNumber,
                            dialogEmail
                        )
                    }
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserInputField(
    fieldLabel: String,
    fieldValue: String,
    keyboardOptions: KeyboardOptions,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = fieldLabel, style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = fieldValue,
            onValueChange = { onValueChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = keyboardOptions
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
private fun EmployeeEditDialogPreview() {
    ProjectCerberusAdminComposeTheme {
        EmployeeEditDialog(
            firstName = "Charlie",
            lastName = "Castro",
            phoneNumber = "787-509-1919",
            email = "ccspart2@gmail.com",
            onConfirmEditRequest = { _, _, _, _ -> },
            onDismissRequest = {}
        )
    }
}

@Preview
@Composable
private fun UserInputFieldPreview() {
    ProjectCerberusAdminComposeTheme {
        UserInputField(
            fieldLabel = "First Name",
            fieldValue = "",
            keyboardOptions = KeyboardOptions.Default,
            onValueChanged = {}
        )
    }
}
