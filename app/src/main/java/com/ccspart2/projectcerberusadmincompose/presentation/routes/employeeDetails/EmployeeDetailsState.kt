package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import com.ccspart2.projectcerberusadmincompose.data.model.Employee

data class EmployeeDetailsState(
    val selectedEmployee: Employee? = null,
    val isLoading: Boolean = true
)
