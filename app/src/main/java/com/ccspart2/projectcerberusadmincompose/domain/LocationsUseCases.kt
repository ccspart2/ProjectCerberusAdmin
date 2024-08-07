package com.ccspart2.projectcerberusadmincompose.domain

import com.ccspart2.projectcerberusadmincompose.data.model.Location
import com.ccspart2.projectcerberusadmincompose.data.repository.LocationsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationsUseCases
@Inject
constructor(
    private val repository: LocationsRepository,
) {
    fun getAllLocations(
        onSuccess: (List<Location>) -> Unit,
        onError: (Exception) -> Unit,
    ): Flow<Unit> = flow {
        try {
            repository.getAllLocations().collect { response ->
                onSuccess(response)
                emit(Unit)
            }
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun addLocation(
        location: Location,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,
    ) {
        try {
            repository.addLocation(location)
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun getLocationById(
        locationId: String,
        onSuccess: (Location?) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val locationResponse = repository.getLocationById(locationId = locationId)
            onSuccess(locationResponse)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun updateLocation(
        locationId: String,
        updatedLocation: Location,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,
    ) {
        try {
            repository.updateLocation(locationId, updatedLocation)
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun deleteLocation(
        locationId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        try {
            repository.removeLocation(locationId)
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        }
    }
}
