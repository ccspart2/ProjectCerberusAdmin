package com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee

data class AddNewEmployeeState(
    val isInvalidData: Boolean = false,
    val isLoading: Boolean = false,
    val employeeUploadState: EmployeeUploadState = EmployeeUploadState.PENDING
)

enum class EmployeeUploadState {
    PENDING,
    SUCCESS,
    ERROR
}
