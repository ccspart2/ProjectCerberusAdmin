package com.ccspart2.projectcerberusadmincompose.data.source

import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface EmployeeDataSource {
    fun getData(): Flow<List<Employee>>
    suspend fun addData(model: Employee)
}

class EmployeeDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : EmployeeDataSource {

    override fun getData(): Flow<List<Employee>> = flow {
        val snapshot = firestore.collection("employees")
            .get()
            .await()
        val data = snapshot.documents.map { document ->
            document.toObject(Employee::class.java)!!
        }
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun addData(model: Employee) {
        withContext(Dispatchers.IO) {
            firestore.collection("employees")
                .add(model)
                .await()
        }
    }
}
