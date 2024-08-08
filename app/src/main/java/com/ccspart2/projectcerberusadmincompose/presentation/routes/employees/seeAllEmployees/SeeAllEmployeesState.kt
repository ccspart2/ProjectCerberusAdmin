package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.seeAllEmployees

import com.ccspart2.projectcerberusadmincompose.data.model.Employee

data class SeeAllEmployeesState(
    val employeeList: List<Employee> = listOf(),
    val isLoading: Boolean = true
)
