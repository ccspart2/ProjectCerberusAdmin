package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.addNewEmployee

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
