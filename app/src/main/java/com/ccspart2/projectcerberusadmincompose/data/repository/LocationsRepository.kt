package com.ccspart2.projectcerberusadmincompose.data.repository

import com.ccspart2.projectcerberusadmincompose.data.model.Location
import com.ccspart2.projectcerberusadmincompose.data.source.LocationDataSource
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun getAllLocations(): Flow<List<Location>>

    suspend fun addLocation(location: Location)

    suspend fun getLocationById(locationId: String): Location?

    suspend fun updateLocation(locationId: String, updatedLocation: Location)

    suspend fun removeLocation(locationId: String)
}

class LocationRepositoryImpl(private val dataSource: LocationDataSource) : LocationsRepository {
    override fun getAllLocations(): Flow<List<Location>> = dataSource.getAllLocations()

    override suspend fun addLocation(location: Location) {
        dataSource.addLocation(location)
    }

    override suspend fun getLocationById(locationId: String): Location? {
        return dataSource.getLocationById(locationId)
    }

    override suspend fun updateLocation(locationId: String, updatedLocation: Location) {
        return dataSource.updateLocation(locationId, updatedLocation)
    }

    override suspend fun removeLocation(locationId: String) {
        return dataSource.removeLocation(locationId)
    }
}
