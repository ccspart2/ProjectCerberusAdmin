package com.ccspart2.projectcerberusadmincompose.data.model

data class Employee(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val isAdmin: Boolean = false
) {
    companion object {
        fun sortEmployeesByName(employees: List<Employee>): List<Employee> {
            return employees.sortedWith(compareBy<Employee> { it.firstName }.thenBy { it.lastName })
        }
        fun mock(): Employee {
            return Employee(
                id = "12233445233525",
                firstName = "Charlie",
                lastName = "Castro",
                phoneNumber = "7875091818",
                email = "ccspart2@gmail.com",
                isAdmin = true
            )
        }
    }
}
