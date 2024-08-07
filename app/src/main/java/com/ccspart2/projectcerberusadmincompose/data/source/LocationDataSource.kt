package com.ccspart2.projectcerberusadmincompose.data.source

import com.ccspart2.projectcerberusadmincompose.data.model.Definitions.FirebaseCollectionLabels.LOCATIONS
import com.ccspart2.projectcerberusadmincompose.data.model.Location
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface LocationDataSource {
    fun getAllLocations(): Flow<List<Location>>

    suspend fun addLocation(location: Location)

    suspend fun getLocationById(locationId: String): Location?

    suspend fun updateLocation(locationId: String, updatedLocation: Location)

    suspend fun removeLocation(locationId: String)
}

class LocationDataSourceImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
) : LocationDataSource {
    override fun getAllLocations(): Flow<List<Location>> {
        return flow {
                val snapshot = firestore.collection(LOCATIONS.label).get().await()
                val data =
                    snapshot.documents.map { document -> document.toObject(Location::class.java)!! }
                emit(data)
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun addLocation(location: Location) {
        withContext(Dispatchers.IO) {
            val newDocRef = firestore.collection(LOCATIONS.label).document()
            val updatedModel = location.copy(id = newDocRef.id)

            newDocRef.set(updatedModel).await()
        }
    }

    override suspend fun getLocationById(locationId: String): Location? {
        return withContext(Dispatchers.IO) {
            val document = firestore.collection(LOCATIONS.label).document(locationId).get().await()
            document.toObject(Location::class.java)?.copy(id = document.id)
        }
    }

    override suspend fun updateLocation(locationId: String, updatedLocation: Location) {
        withContext(Dispatchers.IO) {
            firestore
                .collection(LOCATIONS.label)
                .document(locationId)
                .set(updatedLocation, SetOptions.merge())
                .await()
        }
    }

    override suspend fun removeLocation(locationId: String) {
        withContext(Dispatchers.IO) {
            firestore.collection(LOCATIONS.label).document(locationId).delete().await()
        }
    }
}
