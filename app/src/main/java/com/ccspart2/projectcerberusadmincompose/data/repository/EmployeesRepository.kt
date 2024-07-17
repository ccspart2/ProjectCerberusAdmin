package com.ccspart2.projectcerberusadmincompose.data.repository

import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.data.source.EmployeeDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface EmployeesRepository {
    fun getAllEmployees(): Flow<List<Employee>>
    suspend fun addEmployee(employee: Employee)
}

class EmployeeRepositoryImpl @Inject constructor(
    private val dataSource: EmployeeDataSource
) : EmployeesRepository {

    override fun getAllEmployees(): Flow<List<Employee>> {
        return dataSource.getAllEmployees()
    }

    override suspend fun addEmployee(employee: Employee) {
        dataSource.addEmployee(employee)
    }
}
