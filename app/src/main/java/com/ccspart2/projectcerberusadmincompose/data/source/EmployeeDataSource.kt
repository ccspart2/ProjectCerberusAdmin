package com.ccspart2.projectcerberusadmincompose.data.source

import com.ccspart2.projectcerberusadmincompose.data.model.Definitions.FirebaseCollectionLabels.EMPLOYEES
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface EmployeeDataSource {
    fun getAllEmployees(): Flow<List<Employee>>

    suspend fun addEmployee(employee: Employee)

    suspend fun getEmployeeById(employeeId: String): Employee?

    suspend fun updateEmployee(
        employeeId: String,
        updatedEmployee: Employee,
    )

    suspend fun removeEmployee(employeeId: String)
}

class EmployeeDataSourceImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
) : EmployeeDataSource {
    override fun getAllEmployees(): Flow<List<Employee>> =
        flow {
                val snapshot = firestore.collection(EMPLOYEES.label).get().await()
                val data =
                    snapshot.documents.map { document -> document.toObject(Employee::class.java)!! }
                emit(data)
            }
            .flowOn(Dispatchers.IO)

    override suspend fun addEmployee(employee: Employee) {
        withContext(Dispatchers.IO) {
            val newDocRef = firestore.collection(EMPLOYEES.label).document()
            val updatedModel = employee.copy(id = newDocRef.id)

            newDocRef.set(updatedModel).await()
        }
    }

    override suspend fun getEmployeeById(employeeId: String): Employee? =
        withContext(Dispatchers.IO) {
            val document = firestore.collection(EMPLOYEES.label).document(employeeId).get().await()
            document.toObject(Employee::class.java)?.copy(id = document.id)
        }

    override suspend fun updateEmployee(
        employeeId: String,
        updatedEmployee: Employee,
    ) {
        withContext(Dispatchers.IO) {
            firestore
                .collection(EMPLOYEES.label)
                .document(employeeId)
                .set(updatedEmployee, SetOptions.merge())
                .await()
        }
    }

    override suspend fun removeEmployee(employeeId: String) {
        withContext(Dispatchers.IO) {
            firestore.collection(EMPLOYEES.label).document(employeeId).delete().await()
        }
    }
}
