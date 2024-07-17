package com.ccspart2.projectcerberusadmincompose.domain

import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.data.repository.EmployeesRepository
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EmployeesUseCases @Inject constructor(
    private val repository: EmployeesRepository
) {
    fun getAllEmployees(
        onSuccess: (List<Employee>) -> Unit,
        onError: (Exception) -> Unit
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
        onError: (Exception) -> Unit
    ) {
        try {
            repository.addEmployee(employee)
            LogUtils.error("The Network call was successful", "EmployeesUseCases")
            onSuccess()
        } catch (e: Exception) {
            LogUtils.error("Network Exception: $e", "EmployeesUseCases")
            onError(e)
        }
    }
}
