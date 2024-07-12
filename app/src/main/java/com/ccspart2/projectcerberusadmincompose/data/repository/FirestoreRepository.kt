package com.ccspart2.projectcerberusadmincompose.data.repository

import com.ccspart2.projectcerberusadmincompose.data.model.FirestoreModel
import com.ccspart2.projectcerberusadmincompose.data.source.FirestoreDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun getData(): Flow<List<FirestoreModel>>
    suspend fun addData(model: FirestoreModel)
}

class FirestoreRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
) : FirestoreRepository {

    override fun getData(): Flow<List<FirestoreModel>> {
        return dataSource.getData()
    }

    override suspend fun addData(model: FirestoreModel) {
        dataSource.addData(model)
    }
}
