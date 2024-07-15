package com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee

sealed class AddNewEmployeeEvent {

    class onSaveButtonClicked(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String,
        val isAdmin: Boolean
    ) : AddNewEmployeeEvent()
}
