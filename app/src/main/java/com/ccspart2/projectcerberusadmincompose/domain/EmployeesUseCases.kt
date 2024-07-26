package com.ccspart2.projectcerberusadmincompose.domain

import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.data.repository.EmployeesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EmployeesUseCases
@Inject
constructor(
    private val repository: EmployeesRepository,
) {
    fun getAllEmployees(
        onSuccess: (List<Employee>) -> Unit,
        onError: (Exception) -> Unit,
    ): Flow<Unit> = flow {
        try {
            repository.getAllEmployees().collect { response ->
                onSuccess(response)
                emit(Unit)
            }
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun addEmployee(
        employee: Employee,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,
    ) {
        try {
            repository.addEmployee(employee)
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun getEmployeeById(
        employeeId: String,
        onSuccess: (Employee?) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val employeeResponse = repository.getEmployeeById(employeeId = employeeId)
            onSuccess(employeeResponse)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun updateEmployee(
        id: String,
        updatedEmployee: Employee,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,
    ) {
        try {
            repository.updateEmployee(id, updatedEmployee)
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun deleteEmployee(
        id: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit) {
        try {
            repository.removeEmployee(id)
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }
}
