package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import com.ccspart2.projectcerberusadmincompose.data.model.Employee

data class EmployeeDetailsState(
    val selectedEmployee: Employee? = null,
    val employeeDetailsEditState: EmployeeDetailsEditState = EmployeeDetailsEditState.PENDING
)

enum class EmployeeDetailsEditState {
    PENDING,
    LOADING,
    DISPLAY_EDIT_DIALOG,
    DISPLAY_INVALID_INPUT_DIALOG,
    DISPLAY_SUCCESS_EDIT_DIALOG,
    DISPLAY_ERROR_EDIT_DIALOG,
    DISPLAY_DELETE_DIALOG,
    DISPLAY_SUCCESS_DELETE_DIALOG,
    DISPLAY_ERROR_DELETE_DIALOG,
}
