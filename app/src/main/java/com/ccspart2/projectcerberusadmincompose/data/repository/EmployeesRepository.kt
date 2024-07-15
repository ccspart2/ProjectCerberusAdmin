package com.ccspart2.projectcerberusadmincompose.data.repository

import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.data.source.EmployeeDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface EmployeesRepository {
    fun getData(): Flow<List<Employee>>
    suspend fun addData(model: Employee)
}

class EmployeeRepositoryImpl @Inject constructor(
    private val dataSource: EmployeeDataSource
) : EmployeesRepository {

    override fun getData(): Flow<List<Employee>> {
        return dataSource.getData()
    }

    override suspend fun addData(model: Employee) {
        dataSource.addData(model)
    }
}
