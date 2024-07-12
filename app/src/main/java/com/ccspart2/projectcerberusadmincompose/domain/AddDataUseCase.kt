package com.ccspart2.projectcerberusadmincompose.domain

import com.ccspart2.projectcerberusadmincompose.data.model.FirestoreModel
import com.ccspart2.projectcerberusadmincompose.data.repository.FirestoreRepository
import javax.inject.Inject

class AddDataUseCase @Inject constructor(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(model: FirestoreModel) {
        repository.addData(model)
    }
}
