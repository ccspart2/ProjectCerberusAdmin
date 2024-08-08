package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.employeeDetails

sealed class EmployeeDetailsEvent {

    data class OnDisplayEditDialog(val action: Boolean) : EmployeeDetailsEvent()

    data class OnConfirmEditDialog(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String
    ) : EmployeeDetailsEvent()

    object OnInvalidInputDismiss : EmployeeDetailsEvent()

    object OnDisplayDeleteDialog : EmployeeDetailsEvent()

    object OnConfirmDeleteDialog : EmployeeDetailsEvent()

    object OnDismissDeleteDialog : EmployeeDetailsEvent()
}
