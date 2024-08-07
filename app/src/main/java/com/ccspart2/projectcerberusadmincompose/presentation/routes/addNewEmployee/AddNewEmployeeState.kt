package com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee

data class AddNewEmployeeState(
    val employeeUploadState: EmployeeUploadState = EmployeeUploadState.PENDING
)

enum class EmployeeUploadState {
    PENDING,
    SUCCESS,
    ERROR,
    INVALID_INPUT,
    LOADING
}
