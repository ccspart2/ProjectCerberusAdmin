package com.ccspart2.projectcerberusadmincompose.data.source

import com.ccspart2.projectcerberusadmincompose.data.model.FirestoreModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

// data/source/FirestoreDataSource.kt
interface FirestoreDataSource {
    fun getData(): Flow<List<FirestoreModel>>
    suspend fun addData(model: FirestoreModel)
}

class FirestoreDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreDataSource {

    override fun getData(): Flow<List<FirestoreModel>> = flow {
        val snapshot = firestore.collection("demo")
            .get()
            .await()
        val data = snapshot.documents.map { document ->
            document.toObject(FirestoreModel::class.java)!!
        }
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun addData(model: FirestoreModel) {
        withContext(Dispatchers.IO) {
            firestore.collection("demo")
                .add(model)
                .await()
        }
    }
}
