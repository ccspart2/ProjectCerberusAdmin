package com.ccspart2.projectcerberusadmincompose.domain

import com.ccspart2.projectcerberusadmincompose.data.model.FirestoreModel
import com.ccspart2.projectcerberusadmincompose.data.repository.FirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetDataUseCase @Inject constructor(
    private val repository: FirestoreRepository
) {
    operator fun invoke(): Flow<List<FirestoreModel>> {
        return repository.getData()
    }
}
