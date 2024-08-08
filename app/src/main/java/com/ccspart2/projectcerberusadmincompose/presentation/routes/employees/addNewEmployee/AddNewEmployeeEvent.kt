package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.addNewEmployee

sealed class AddNewEmployeeEvent {

    data class OnSaveButtonClicked(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String,
        val isAdmin: Boolean
    ) : AddNewEmployeeEvent()

    object OnInputValidationConfirmButtonCLicked : AddNewEmployeeEvent()
}
