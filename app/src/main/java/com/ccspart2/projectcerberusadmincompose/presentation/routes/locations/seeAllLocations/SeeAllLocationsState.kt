package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.seeAllLocations

import com.ccspart2.projectcerberusadmincompose.data.model.Location

data class SeeAllLocationsState(
    val locationList: List<Location> = listOf(),
    val locationUploadState: LocationUploadState = LocationUploadState.LOADING
)

enum class LocationUploadState {
    LOADING,
    SUCCESS,
    ERROR,
}
