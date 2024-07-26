package com.ccspart2.projectcerberusadmincompose.data.repository

import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.data.source.EmployeeDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface EmployeesRepository {
    fun getAllEmployees(): Flow<List<Employee>>

    suspend fun addEmployee(employee: Employee)

    suspend fun getEmployeeById(employeeId: String): Employee?

    suspend fun updateEmployee(
        employeeId: String,
        updatedEmployee: Employee,
    )

    suspend fun removeEmployee(employeeId: String)
}

class EmployeeRepositoryImpl
@Inject
constructor(
    private val dataSource: EmployeeDataSource,
) : EmployeesRepository {
    override fun getAllEmployees(): Flow<List<Employee>> = dataSource.getAllEmployees()

    override suspend fun addEmployee(employee: Employee) {
        dataSource.addEmployee(employee)
    }

    override suspend fun getEmployeeById(employeeId: String): Employee? =
        dataSource.getEmployeeById(employeeId)

    override suspend fun updateEmployee(
        employeeId: String,
        updatedEmployee: Employee,
    ) {
        dataSource.updateEmployee(
            employeeId,
            updatedEmployee,
        )
    }

    override suspend fun removeEmployee(employeeId: String) {
        dataSource.removeEmployee(employeeId)
    }
}
